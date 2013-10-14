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
	
	private ArrayList<Prevision> previsiones;
	private XMLstream xmlStream;
	
	public JAEMET() {
		previsiones = new ArrayList<Prevision>();
		xmlStream = new XMLstream(previsiones);
	}
	
	/**
	 * Devuelve un ArrayList de objetos tipo Prevision 
	 * <p>
	 * @return ArrayList&lt;Prevision&gt;
	 */	
	public ArrayList<Prevision> getPrevisiones() {
		
		return previsiones;
	}
	
	/**
	 * Previsi&oacute;n para los pr&oacute;ximos siete d&iacute;as del municipio puesto 
	 * <p>
	 * @param  municipio  ID del municipio (sin ning&uacute;n zero delante)
	 */	
	public void setMunicipio(int municipio) {
		
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
	
}
