package com.javierllorente.jaemet;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.stream.*;

/**
 * @author javier
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
	
	public ArrayList<Prevision> getPrevisiones() {
		
		return previsiones;
	}
	
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
	
	public void setMunicipios(int begin, int end) {

		for (int i=begin; i<=end; i++) {
			setMunicipio(i);
		}
	}	
	
}
