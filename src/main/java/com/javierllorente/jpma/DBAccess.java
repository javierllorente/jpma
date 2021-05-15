/*
 * Copyright (C) 2012-2021 Javier Llorente <javier@opensuse.org>
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para conectarse y actualizar la base de datos
 * 
 * @author javier
 */
public class DBAccess {

    private Connection dbConnection = null;
    private PreparedStatement preparedStatement = null;

    public DBAccess(String url, String username, String password) throws 
            ClassNotFoundException, SQLException {
        getConnection(url, username, password);
    }

    private void getConnection(String url, String username, String password)
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = (Connection) DriverManager.getConnection(url,
                username, password);
    }

    public void alimentarLocalidad(Prevision prevision) {
        try {
            String sql = "INSERT INTO localidad(id, nombre, provincia) "
                    + "VALUES(?, ?, ?)";
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, prevision.getId());
            preparedStatement.setString(2, prevision.getMunicipio());
            preparedStatement.setString(3, prevision.getProvincia());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void alimentarPrediccion(Prevision prevision) {
        try {
            String sql = "INSERT INTO prediccion(fecha, estado_cielo, t_max, "
                    + "t_min, sens_termica_max, sens_termica_min, "
                    + "humedad_rel_max, humedad_rel_min, id, id_prediccion, "
                    + "viento_dir) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, prevision.getFecha());
            preparedStatement.setString(2, prevision.getEstado_cielo());
            preparedStatement.setString(3, prevision.getT_max());
            preparedStatement.setString(4, prevision.getT_min());
            preparedStatement.setString(5, prevision.getSt_max());
            preparedStatement.setString(6, prevision.getSt_min());
            preparedStatement.setString(7, prevision.getHr_max());
            preparedStatement.setString(8, prevision.getHr_min());
            preparedStatement.setString(9, prevision.getId());
            preparedStatement.setInt(10, prevision.getId_prediccion());
            preparedStatement.setString(11, "");
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Prevision prevision) {
        try {
            String sql = "UPDATE prediccion SET fecha=?, estado_cielo=?, "
                    + "t_max=?, t_min=?, sens_termica_max=?, sens_termica_min=?,"
                    + " humedad_rel_max=?, humedad_rel_min=? "
                    + "WHERE id=? AND id_prediccion=?";
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, prevision.getFecha());
            preparedStatement.setString(2, prevision.getEstado_cielo());
            preparedStatement.setString(3, prevision.getT_max());
            preparedStatement.setString(4, prevision.getT_min());
            preparedStatement.setString(5, prevision.getSt_max());
            preparedStatement.setString(6, prevision.getSt_min());
            preparedStatement.setString(7, prevision.getHr_max());
            preparedStatement.setString(8, prevision.getHr_min());
            preparedStatement.setString(9, prevision.getId());
            preparedStatement.setInt(10, prevision.getId_prediccion());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
