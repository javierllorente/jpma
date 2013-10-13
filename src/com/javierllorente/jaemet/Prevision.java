package com.javierllorente.jaemet;

/**
 * @author javier
 *
 */

public class Prevision {

	private int id_prediccion;
	private String id;
	private String municipio;
	private String provincia;
	private String fecha;
	private String estado_cielo;
	private String t_max;
	private String t_min;
	private String st_max;
	private String st_min;
	private String hr_max;
	private String hr_min;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getEstado_cielo() {
		return estado_cielo;
	}
	public void setEstado_cielo(String estado_cielo) {
		this.estado_cielo = estado_cielo;
	}
	public String getT_max() {
		return t_max;
	}
	public void setT_max(String t_max) {
		this.t_max = t_max;
	}
	public String getT_min() {
		return t_min;
	}
	public void setT_min(String t_min) {
		this.t_min = t_min;
	}
	public String getSt_max() {
		return st_max;
	}
	public void setSt_max(String st_max) {
		this.st_max = st_max;
	}
	public String getSt_min() {
		return st_min;
	}
	public void setSt_min(String st_min) {
		this.st_min = st_min;
	}
	public String getHr_max() {
		return hr_max;
	}
	public void setHr_max(String hr_max) {
		this.hr_max = hr_max;
	}
	public String getHr_min() {
		return hr_min;
	}
	public void setHr_min(String hr_min) {
		this.hr_min = hr_min;
	}
	public int getId_prediccion() {
		return id_prediccion;
	}
	public void setId_prediccion(int id_prediccion) {
		this.id_prediccion = id_prediccion;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
}
