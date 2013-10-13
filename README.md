jaemet
======

Biblioteca para obtener y procesar los partes meteorológicos de la AEMET

Copyright (C) 2012-2013 Javier Llorente <javier@opensuse.org>

Esta biblioteca está bajo la licencia GPL 2.0 y 3.0. 
Para más detalles, echa un vistazo a gpl-2.0.txt y gpl-3.0.txt 




Ejemplo de uso
--------------
```java
JAEMET jaemet = new JAEMET();
ArrayList<Prevision> previsiones = new ArrayList<>();

jaemet.setMunicipio(28079);
jaemet.setMunicipios(2001, 2086);
previsiones.addAll(jaemet.getPrevisiones());

for (Prevision prevision : previsiones) {
	System.out.print(prevision.getId_prediccion() + " " + prevision.getId() + " " + prevision.getMunicipio() + " ");
	System.out.println(prevision.getFecha() + " " + prevision.getEstado_cielo() + " " + prevision.getT_max() + "C " + prevision.getT_min() + "C");
			
}
```

Licencia
--------
Esta biblioteca está bajo la licencia GPL 2.0 y 3.0. 
Para más detalles, echa un vistazo a gpl-2.0.txt y gpl-3.0.txt 


