package com.javierllorente.jaemet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author Javier Llorente
 *
 */

public class XMLstream {
	
	private HttpURLConnection connection = null;
	private ArrayList<Prevision> previsiones;
	private InputStream content = null;
	int id_prediccion = 1;	
	
	public XMLstream() {
		
	}
	
	public XMLstream(ArrayList<Prevision> previsiones) {
		super();
		this.previsiones = previsiones;
	}
	
	public void setUrl(URL url) {		

		try {
			connection = (HttpURLConnection) url.openConnection();
//			System.out.println("Response code: " + connection.getResponseCode());
			content = (InputStream) connection.getInputStream();
			
		} catch (FileNotFoundException e) {
			content = null;
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	ArrayList<Prevision> getContent() {		

		if (content!=null) {

			Prevision prevision = null;
			String id_municipio = null;
			String municipio = null;
			String provincia = null;

			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader xmlStreamReader;
			
			try {
				xmlStreamReader = inputFactory.createXMLStreamReader(content);

				while (xmlStreamReader.hasNext()) {			

					int eventType = xmlStreamReader.next();

					while((eventType == XMLStreamConstants.CHARACTERS && xmlStreamReader.isWhiteSpace())
							|| (eventType == XMLStreamConstants.CDATA && xmlStreamReader.isWhiteSpace()) 
							|| eventType == XMLStreamConstants.SPACE
							|| eventType == XMLStreamConstants.PROCESSING_INSTRUCTION
							|| eventType == XMLStreamConstants.COMMENT
							) {
						eventType = xmlStreamReader.next();
					}

					if (eventType == XMLStreamConstants.START_ELEMENT) {

						if (xmlStreamReader.getLocalName()=="root") {
							id_municipio = xmlStreamReader.getAttributeValue(0);

						} else if(xmlStreamReader.getLocalName()=="nombre") {
							municipio = xmlStreamReader.getElementText();

						} else if (xmlStreamReader.getLocalName()=="provincia") {
							provincia = xmlStreamReader.getElementText();

						} else if (xmlStreamReader.getLocalName()=="dia") {
							prevision = new Prevision();
							prevision.setFecha(xmlStreamReader.getAttributeValue(0));
							prevision.setId_prediccion(id_prediccion);
							prevision.setId(id_municipio);
							prevision.setMunicipio(municipio);
							prevision.setProvincia(provincia);

						} else if (xmlStreamReader.getLocalName()=="estado_cielo") {

							for (int i = 0; i < xmlStreamReader.getAttributeCount(); i++) {						

								// Guarda el valor de estado_cielo si su valor es 12-18
								// o bien si sólo tiene un atributo

								if (xmlStreamReader.getAttributeValue(i).equals("12-18")) {
									i++;
									prevision.setEstado_cielo(xmlStreamReader.getAttributeValue(i));

								} else if (xmlStreamReader.getAttributeValue(i).equals("12-24")) {
									i++;
									prevision.setEstado_cielo(xmlStreamReader.getAttributeValue(i));

								} else if (xmlStreamReader.getAttributeCount()==1) {
									prevision.setEstado_cielo(xmlStreamReader.getAttributeValue(i));

								}

							} // end for

						} else if (xmlStreamReader.getLocalName()=="temperatura") {

							xmlStreamReader.nextTag();
							prevision.setT_max(xmlStreamReader.getElementText());

							xmlStreamReader.nextTag();
							prevision.setT_min(xmlStreamReader.getElementText());

						} else if (xmlStreamReader.getLocalName()=="sens_termica") {

							xmlStreamReader.nextTag();
							prevision.setSt_max(xmlStreamReader.getElementText());

							xmlStreamReader.nextTag();
							prevision.setSt_min(xmlStreamReader.getElementText());

						} else if (xmlStreamReader.getLocalName()=="humedad_relativa") {

							xmlStreamReader.nextTag();
							prevision.setHr_max(xmlStreamReader.getElementText());

							xmlStreamReader.nextTag();
							prevision.setHr_min(xmlStreamReader.getElementText());

							id_prediccion++;
							previsiones.add(prevision);

						}				

					} // end START_ELEMENT		

				} // end while

			} catch (XMLStreamException e) {
				// En caso de que haya algún problema con el XML, incrementar id_prediccion
				// para que la relación id e id_prediccion sea la correcta. Ésto es necesario
				// hacerlo para la función update de la clase DBaccess
				id_prediccion++;
				e.printStackTrace();
			}
		}

		return previsiones;
		
	} //end getContent

}
