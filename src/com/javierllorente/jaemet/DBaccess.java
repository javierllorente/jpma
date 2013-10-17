package com.javierllorente.jaemet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Javier Llorente
 *
 */

public class DBaccess {
	
	private Connection dbConnection = null;
	private PreparedStatement preparedStatement = null;
	
	public DBaccess(String server, String db, String username, String password) {

		getConnection(server, db, username, password);
	}

	private void getConnection(String server, String db, String username, String password) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbConnection = (Connection) DriverManager.getConnection(server+db,username,password);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alimentarLocalidad(Prevision prevision) {
		
		String sql = "INSERT INTO localidad(id, nombre, provincia) VALUES(?, ?, ?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, prevision.getId());
			preparedStatement.setString(2, prevision.getMunicipio());
			preparedStatement.setString(3, prevision.getProvincia());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void alimentarPrediccion(Prevision prevision) {

		String sql = "INSERT INTO prediccion(fecha, estado_cielo, t_max, t_min, sens_termica_max, sens_termica_min, humedad_rel_max, humedad_rel_min, id, id_prediccion, viento_dir) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void update(Prevision prevision) {

		String sql = "UPDATE prediccion SET fecha=?, estado_cielo=?, t_max=?, t_min=?, sens_termica_max=?, sens_termica_min=?, humedad_rel_max=?, humedad_rel_min=? WHERE id=? AND id_prediccion=?";
		
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
