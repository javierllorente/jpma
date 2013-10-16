package com.javierllorente.jaemet;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.stream.*;

/**
 * @author Javier Llorente
 *
 */

public class JAEMET {
	
	private static final String AEMET_URL = "http://www.aemet.es/xml/municipios/localidad_";
	
	private ArrayList<Integer> listaMunicipios;
	private ArrayList<Prevision> previsiones;
	private XMLstream xmlStream;
	
	public enum Provincia {
		ALAVA(1), ALBACETE(2), ALICANTE(3), ALMERIA(4), AVILA(5), BADAJOZ(6), BALEARES(7), 
		BARCELONA(8), BURGOS(9), CACERES(10), CADIZ(11), CASTELLON(12), CIUDAD_REAL(13), 
		CORDOBA(14), LA_CORUNA(15), CUENCA(16), GERONA(17), GRANADA(18), GUADALAJARA(19), 
		GUIPUZCOA(20), HUELVA(21), HUESCA(22), JAEN(23), LEON(24), LERIDA(25), LOGRONO(26), 
		LUGO(27), MADRID(28), MALAGA(29), MURCIA(30), NAVARRA(31), ORENSE(32), ASTURIAS(33), 
		PALENCIA(34), LAS_PALMAS(35), PONTEVEDRA(36), SALAMANCA(37), TENERIFE(38), 
		SANTANDER(39), SEGOVIA(40), SEVILLA(41), SORIA(42),	TARRAGONA(43), TERUEL(44), 
		TOLEDO(45),	VALENCIA(46), VALLADOLID(47), VIZCAYA(48), ZAMORA(49), ZARAGOZA(50), 
		CEUTA(51), MELILLA(52);

		private int id;

		private Provincia(int id) {
			this.id = id;
		}
		
		public int getId() {
			return id;
		}
	}; 
	
	public JAEMET() {
		listaMunicipios = new ArrayList<Integer>();
		previsiones = new ArrayList<Prevision>();
		xmlStream = new XMLstream(previsiones);
	}
	
	/**
	 * Devuelve un ArrayList de objetos tipo Prevision 
	 * <p>
	 * @return ArrayList&lt;Prevision&gt;
	 */	
	public ArrayList<Prevision> getPrevisiones() {
		
		for (int municipio : listaMunicipios) {

			try {			
				xmlStream.setUrl(new URL(AEMET_URL + String.format("%05d", municipio) + ".xml"));
				previsiones = xmlStream.getContent();

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		listaMunicipios.clear();
		
		return previsiones;
	}
	
	/**
	 * Previsi&oacute;n para los pr&oacute;ximos siete d&iacute;as del municipio puesto 
	 * <p>
	 * @param  municipio  ID del municipio (sin ning&uacute;n zero delante)
	 */	
	public void setMunicipio(int municipio) {
		
		listaMunicipios.add(municipio);

	}
	
	/**
	 * Previsi&oacute;n para los pr&oacute;ximos siete d&iacute;as de los municipios puestos
	 * <br> En caso de que el ID no exista, continuar&aacute; con el siguiente.
	 * <p>
	 * @param  begin incio del rango de  municipios
	 * @param  end fin del rango de municipios
	 */	
	public void setMunicipios(int begin, int end) {

		for (int i=begin; i<=end; i++) {
			setMunicipio(i);
		}
	}
	/**
	 * Futura funci&oacute;n
	 * @param provincia
	 */
	public void setProvincia(Provincia provincia) {
		
//		Municipios m = new Municipios();
//		listaMunicipios.addAll(m.getMunicipios(provincia));		
		
	}
	
}
