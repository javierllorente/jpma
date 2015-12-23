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
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase principal (la fachada)
 * 
 * @author javier
 */
public class PMA {

    private static final String AEMET_PREV_MUNICIPIO_URL = "http://www.aemet.es/xml/municipios/localidad_";
    private HttpURLConnection connection = null;
    private final ArrayList<Integer> listaMunicipios;
    private final XMLStream xmlStream;

    public PMA() {
        listaMunicipios = new ArrayList<>();
        xmlStream = new XMLStream();
    }
    
    InputStream getRequest(URL url) throws MunicipioIdNotFoundException {
        InputStream content = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            /* 
             * HTTP status code definitions: 
             * http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html 
             */
            int httpCode = connection.getResponseCode();
            boolean isError = httpCode >= 400;
            if (isError) {
                String errorMessage = connection.getResponseMessage();
                throw new MunicipioIdNotFoundException("Error " + httpCode + " - " + errorMessage);
            }            
            content = (InputStream) connection.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(XMLStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }
    
    /**
     * Devuelve un ArrayList de objetos tipo Prevision
     * <p>
     * @return ArrayList&lt;Prevision&gt;
     */
    public ArrayList<Prevision> getPrevisiones() {
        ArrayList<Prevision> previsiones = new ArrayList<>();
        for (int municipio : listaMunicipios) {
            try {
                URL url = new URL(AEMET_PREV_MUNICIPIO_URL + String.format("%05d", municipio) + ".xml");
                InputStream data = getRequest(url);
                previsiones.addAll(xmlStream.parsePrevisiones(data));
            } catch (MalformedURLException | MunicipioIdNotFoundException ex) {
                Logger.getLogger(PMA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        listaMunicipios.clear();
        return previsiones;
    }

    /**
     * Previsi&oacute;n para los pr&oacute;ximos siete d&iacute;as del municipio
     * puesto
     * <p>
     * @param municipio ID del municipio (sin ning&uacute;n zero delante)
     */
    public void setMunicipio(int municipio) {
        listaMunicipios.add(municipio);
    }

    /**
     * Previsi&oacute;n para los pr&oacute;ximos siete d&iacute;as de los
     * municipios puestos
     * <br> En caso de que el ID no exista, continuar&aacute; con el siguiente.
     * <p>
     * @param begin incio del rango de municipios
     * @param end fin del rango de municipios
     */
    public void setMunicipios(int begin, int end) {
        for (int i = begin; i <= end; i++) {
            setMunicipio(i);
        }
    }

    /**
     * Previsi&oacute;n para los pr&oacute;ximos siete d&iacute;as de los
     * municipios de la provincia puesta
     * <p>
     * @param provincia Provincia.PROVINCIA (ej: Provincia.MADRID)
     */
    public void setProvincia(Provincia provincia) {
        try {
            listaMunicipios.addAll(Municipios.getIds(provincia));
        } catch (MunicipioIdNotFoundException ex) {
            Logger.getLogger(PMA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Devuelve el n&uacute;mero de municipios añadidos
     * @return int
     */
    public int getMunicipioCount() {
        return listaMunicipios.size();
    }
}
