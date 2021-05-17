jPMA
======
Predicción meteorológica de la AEMET (PMA)

Paquete: com.javierllorente.jpma

Programa para obtener y procesar los partes meteorológicos de la [AEMET](http://www.aemet.es/). Los partes meteorológicos se pueden usar y reproducir siempre que se mencione a la AEMET como autora de los mismos.

Copyright (C) 2012-2021 Javier Llorente <javier@opensuse.org>






Instalación
-------------


Para poder usar jPMA como dependencia en otros proyectos de Maven que tengas en local, 
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

Si no usas Maven, simplemente copia el jar de jpma al directorio correspondiente.


Ejemplos de uso
---------------

Edita jpma.properties; pon los datos de acceso a la base de datos y los municipios/provincias 
de los que quieras obtener previsiones. Normalmente son 
siete por municipio (previsión del tiempo para los próximos siete días).

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

Dependencias
------------
- jsoup para obtener los IDs de todos los municipios de la provincia
- mysql-connector-java si se usa la clase DBAccess (opcional)


Licencia
--------
Esta biblioteca está bajo la licencia GPL 3.0. 
Para más detalles, echa un vistazo a [gpl-3.0.txt](gpl-3.0.txt)

Nota: debes citar a la AEMET como autora de los partes meteorológicos.


