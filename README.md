jPMA
======
Predicción meteorológica de la AEMET (PMA)

Paquete: com.javierllorente.jpma

Biblioteca de Java para obtener y procesar los partes meteorológicos de la [AEMET](http://www.aemet.es/). Los partes meteorológicos se pueden usar y reproducir siempre que se mencione a la AEMET como autora de los mismos.

Copyright (C) 2012-2015 Javier Llorente <javier@opensuse.org>






Instalación
-------------


Para poder usar jPMA como dependencia en otros proyectos que tengas en local, 
tienes que instalarlo en el repositorio local de Maven;

Clona este repositorio
```
git clone https://github.com/javierllorente/jpma.git
```
y ejecuta la siguiente orden dentro del directorio jpma:
```
mvn install
``` 

También puedes instalar jPMA directamente con un IDE que 
soporte [Git](http://git-scm.com/) y [Maven](https://maven.apache.org/) como [NetBeans](http://www.netbeans.org/). 




Ejemplos de uso
---------------

El método `getPrevisiones()` devuelve objetos de tipo `Prevision` que normalmente son 
siete por municipio (previsión del tiempo para los próximos siete días).

Para obtener los partes de todos los municipios de la provincia, ponemos el nombre de la provincia.
```java
PMA pma = new PMA();
ArrayList<Prevision> previsiones = new ArrayList<>();

pma.setProvincia(Provincia.MADRID);
previsiones.addAll(pma.getPrevisiones());

for (Prevision prevision : previsiones) {
	System.out.print(prevision.getId_prediccion() + " " + prevision.getId() + " " + prevision.getMunicipio() + " ");
	System.out.println(prevision.getFecha() + " " + prevision.getEstado_cielo() + " " + prevision.getT_max() + "C " + prevision.getT_min() + "C");
}
```

Si sólo queremos obtener el parte meteorológico de un municipio, usaremos el método `setMunicipio()`.
```java
PMA pma = new PMA();
ArrayList<Prevision> previsiones = new ArrayList<>();

pma.setMunicipio(28079);
previsiones.add(pma.getPrevisiones());
for (Prevision prevision : previsiones) {
	System.out.print(prevision.getId_prediccion() + " " + prevision.getId() + " " + prevision.getMunicipio() + " ");
	System.out.println(prevision.getFecha() + " " + prevision.getEstado_cielo() + " " + prevision.getT_max() + "C " + prevision.getT_min() + "C");
}

```

Base de datos
-------------

Para usar MySQL, tienes que crear una base de datos (pma, por ejemplo)
```
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS \`pma\` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci"
```
e importar la estructura ([pma.sql](db/pma.sql))
```
mysql -u root -p pma < db/pma.sql
```

El último paso sería usar la clase DBAccess para conectarse y actualizar la base de datos;
```java
PMA pma = new PMA();
System.out.println("Obteniendo conexión a la base de datos...");
DBAccess db = new DBAccess("jdbc:mysql://localhost:3306/", "base_de_datos", "usuario", "contraseña");

ArrayList<Prevision> previsiones = new ArrayList<>();
pma.setProvincia(Provincia.MADRID);
System.out.println("Obteniendo previsiones...");
previsiones.addAll(pma.getPrevisiones());

System.out.println("Actualizando la base de datos...");
for (Prevision prevision : previsiones) {
    db.update(prevision);
}
db.closeConnection();

```

Dependencias
------------
- jsoup para obtener los IDs de todos los municipios de la provincia
- mysql-connector-java si se usa la clase DBAccess (opcional)


Licencia
--------
Esta biblioteca está bajo la licencia GPL 3.0. 
Para más detalles, echa un vistazo a [gpl-3.0.txt](gpl-3.0.txt)

Nota: debes citar a la AEMET como autora de los partes meteorológicos.


