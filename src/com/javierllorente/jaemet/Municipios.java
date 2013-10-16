package com.javierllorente.jaemet;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Javier Llorente
 *
 */

public class Municipios {

	private static final String AEMET_URL = "http://www.aemet.es/es/eltiempo/prediccion/municipios?p=";
	private static Document doc = null;
	
	private Municipios() {}

	public static ArrayList<Integer> getIDs(Provincia provincia) {
		
		ArrayList<Integer> idsMunicipios = new ArrayList<Integer>();
		
		try {
			doc = Jsoup.connect(AEMET_URL + provincia.getId()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (doc != null) {

			Elements optionElements = doc.select("div.width250px > select.form_combo_box > option");

			for (Element optionElement : optionElements) {

				final String regex = "\\D";
				String result = optionElement.attr("value");
				String[] items = result.split(regex);

				for (String item : items) {
					if (!item.isEmpty()) {
						idsMunicipios.add(Integer.parseInt(item));
					}
				}
				
			} // for			
		} // if

		return idsMunicipios;
	}

}
