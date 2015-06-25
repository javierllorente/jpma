jAEMET
======

Biblioteca de Java para obtener y procesar los partes meteorológicos de la [AEMET](http://www.aemet.es/)

Copyright (C) 2012-2015 Javier Llorente <javier@opensuse.org>






Ejemplos de uso
---------------

El método `getPrevisiones()` devuelve objetos de tipo `Prevision` que normalmente son 
siete por municipio (previsión del tiempo para los próximos siete días).

Para obtener los partes de todos los municipios de la provincia, ponemos el nombre de la provincia.
```java
AEMET aemet = new AEMET();
ArrayList<Prevision> previsiones = new ArrayList<Prevision>();

aemet.setProvincia(Provincia.MADRID);
previsiones.addAll(aemet.getPrevisiones());

for (Prevision prevision : previsiones) {
	System.out.print(prevision.getId_prediccion() + " " + prevision.getId() + " " + prevision.getMunicipio() + " ");
	System.out.println(prevision.getFecha() + " " + prevision.getEstado_cielo() + " " + prevision.getT_max() + "C " + prevision.getT_min() + "C");
}
```

Si sólo queremos obtener el parte meteorológico de un municipio, usaremos el método `setMunicipio()`.
```java
AEMET aemet = new AEMET();
ArrayList<Prevision> previsiones = new ArrayList<Prevision>();

aemet.setMunicipio(28079);
previsiones.add(aemet.getPrevisiones());
for (Prevision prevision : previsiones) {
	System.out.print(prevision.getId_prediccion() + " " + prevision.getId() + " " + prevision.getMunicipio() + " ");
	System.out.println(prevision.getFecha() + " " + prevision.getEstado_cielo() + " " + prevision.getT_max() + "C " + prevision.getT_min() + "C");
}

```

Dependencias
------------
- jsoup para obtener los IDs de todos los municipios de la provincia
- mysql-connector-java si se usa la clase DBaccess (opcional)


Licencia
--------
Esta biblioteca está bajo la licencia GPL 3.0. 
Para más detalles, echa un vistazo a gpl-3.0.txt 


