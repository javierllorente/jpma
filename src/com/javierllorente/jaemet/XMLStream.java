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
package com.javierllorente.jaemet;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author javier
 */
class XMLStream {
    private HttpURLConnection connection = null;
    private final ArrayList<Prevision> previsiones;
    private InputStream content = null;
    int id_prediccion = 1;

    XMLStream(ArrayList<Prevision> previsiones) {
        this.previsiones = previsiones;
    }

    void setUrl(URL url) {    
        try {
            connection = (HttpURLConnection) url.openConnection();
//          System.out.println("Response code: " + connection.getResponseCode());
            content = (InputStream) connection.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(XMLStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ArrayList<Prevision> getContent() {
        if (content != null) {
            try {
                Prevision prevision = null;
                String id_municipio = null;
                String municipio = null;
                String provincia = null;
                XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                XMLStreamReader xmlStreamReader = inputFactory.createXMLStreamReader(content);

                while (xmlStreamReader.hasNext()) {
                    
                    int eventType = xmlStreamReader.next();
                    
                    while ((eventType == XMLStreamConstants.CHARACTERS && xmlStreamReader.isWhiteSpace())
                            || (eventType == XMLStreamConstants.CDATA && xmlStreamReader.isWhiteSpace())
                            || eventType == XMLStreamConstants.SPACE
                            || eventType == XMLStreamConstants.PROCESSING_INSTRUCTION
                            || eventType == XMLStreamConstants.COMMENT) {
                        eventType = xmlStreamReader.next();
                    }

                    if (eventType == XMLStreamConstants.START_ELEMENT) {

                        if (null != xmlStreamReader.getLocalName()) switch (xmlStreamReader.getLocalName()) {
                            case "root":
                                id_municipio = xmlStreamReader.getAttributeValue(0);
                                break;
                            case "nombre":
                                municipio = xmlStreamReader.getElementText();
                                break;
                            case "provincia":
                                provincia = xmlStreamReader.getElementText();
                                break;
                            case "dia":
                                prevision = new Prevision();
                                prevision.setFecha(xmlStreamReader.getAttributeValue(0));
                                prevision.setId_prediccion(id_prediccion);
                                prevision.setId(id_municipio);
                                prevision.setMunicipio(municipio);
                                prevision.setProvincia(provincia);
                                break;
                            case "estado_cielo":
                                for (int i = 0; i < xmlStreamReader.getAttributeCount(); i++) {                                    
                                    // Guarda el valor de estado_cielo si su valor es 12-18
                                    // o bien si sólo tiene un atributo
                                    if (xmlStreamReader.getAttributeValue(i).equals("12-18")) {
                                        prevision.setEstado_cielo(xmlStreamReader.getAttributeValue(++i));
                                        
                                    } else if (xmlStreamReader.getAttributeValue(i).equals("12-24")) {
                                        prevision.setEstado_cielo(xmlStreamReader.getAttributeValue(++i));
                                        
                                    } else if (xmlStreamReader.getAttributeCount() == 1) {
                                        prevision.setEstado_cielo(xmlStreamReader.getAttributeValue(i));
                                    }                                    
                                } // end for
                                break;
                            case "temperatura":
                                xmlStreamReader.nextTag();
                                prevision.setT_max(xmlStreamReader.getElementText());
                                xmlStreamReader.nextTag();
                                prevision.setT_min(xmlStreamReader.getElementText());
                                break;
                            case "sens_termica":
                                xmlStreamReader.nextTag();
                                prevision.setSt_max(xmlStreamReader.getElementText());
                                xmlStreamReader.nextTag();
                                prevision.setSt_min(xmlStreamReader.getElementText());
                                break;
                            case "humedad_relativa":
                                xmlStreamReader.nextTag();
                                prevision.setHr_max(xmlStreamReader.getElementText());
                                xmlStreamReader.nextTag();
                                prevision.setHr_min(xmlStreamReader.getElementText());
                                id_prediccion++;
                                previsiones.add(prevision);
                                break;
                        }
                        
                    } // end START_ELEMENT
                } // end while
            } catch (XMLStreamException ex) {
                // En caso de que haya algún problema con el XML, incrementar id_prediccion
                // para que la relación id e id_prediccion sea la correcta. Ésto es necesario
                // hacerlo para la función update de la clase DBaccess
                id_prediccion++;
                Logger.getLogger(XMLStream.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        return previsiones;
    } //end getContent
}
