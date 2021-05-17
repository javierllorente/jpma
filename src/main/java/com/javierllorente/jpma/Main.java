/*
 * Copyright (C) 2021 Javier Llorente <javier@opensuse.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.javierllorente.jpma;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javier
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws IOException,
            ClassNotFoundException, SQLException {
        logger.log(Level.INFO, "Running jPMA...");
        PMA pma = new PMA();
        Properties props;

        try (InputStream input = new FileInputStream("jpma.properties")) {
            props = new Properties();
            props.load(input);
        }

        DBAccess db = new DBAccess(props.getProperty("jpma.db.url"),
                props.getProperty("jpma.db.username"), props.getProperty("jpma.db.password"));

        String provinciasStr = props.getProperty("jpma.provincias");
        if (!(provinciasStr == null || provinciasStr.isEmpty()
                || provinciasStr.equals("\"\""))) {
            Set<String> provincias = new HashSet<>(Arrays.asList(provinciasStr
                    .replaceAll("\"", "").split(",")));
            for (String p : provincias) {
                pma.setProvincia(Provincia.valueOf(p));
            }
        }

        String municipiosStr = props.getProperty("jpma.municipios");
        if (!(municipiosStr == null || municipiosStr.isEmpty()
                || municipiosStr.equals("\"\""))) {
            Set<String> municipios = new HashSet<>(Arrays.asList(municipiosStr
                    .replaceAll("\"", "").split(",")));
            for (String m : municipios) {
                pma.setMunicipio(Integer.parseInt(m));
            }
        }

        List<Prevision> previsiones = new ArrayList<>();
        previsiones.addAll(pma.getPrevisiones());
        logger.log(Level.INFO, "Previsiones: {0}", previsiones.size());

        logger.log(Level.INFO, "Updating MySQL DB...");
        for (Prevision prevision : previsiones) {
            db.update(prevision);
        }
        
        db.closeConnection();
    }

}
