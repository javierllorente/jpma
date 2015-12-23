/*
 * Copyright (C) 2012-2015 Javier Llorente <javier@opensuse.org>
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author javier
 */
class Municipios {

    private static final String AEMET_URL = "http://www.aemet.es/es/eltiempo/prediccion/municipios?p=";
    private static Document doc = null;

    static ArrayList<Integer> getIds(Provincia provincia) throws MunicipioIdNotFoundException {
        ArrayList<Integer> idsMunicipios = new ArrayList<>();
        try {
            doc = Jsoup.connect(AEMET_URL + provincia.getId() + "&w=t").get();
        } catch (IOException ex) {
            Logger.getLogger(Municipios.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (doc != null) {
            Elements linkElements = doc.select("tr.localidades > td > a");
            if (linkElements.size()>0) {            
                final String regex = "\\D";
                for (Element linkElement : linkElements) {
                    String result = linkElement.attr("href");
                    String[] items = result.split(regex);
                    for (String item : items) {
                        if (!item.isEmpty()) {
                            idsMunicipios.add(Integer.parseInt(item));
                        }
                    }
                } // for
            } else {
                String msg = "No se pueden obtener los IDs de los municipios de la provincia de ";
                throw new MunicipioIdNotFoundException(msg + provincia.toString());
            }
        } // if
        return idsMunicipios;
    }
}
