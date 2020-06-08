# QuarantApp
Recetas de economía sustentable

## Programacion

El desarrollo de la aplicación se realiza en Java, utilizando el modelo de programación orientada a objetos. Para el desarrollo se emplea Android Studio como editor base y como dispositivos principales de pruebas telefonos Android conectados directamente a la máquina (No se han utilizado emuladores por el alto consumo de recursos que involucran los mismos.).

El usuario tiene acceso a tres categorías principales.

##### Recetas

Puede acceder a las recetas que han subido otros usuarios, y añadir sus recetas propias. 
La actividad principal se compone de un RecyclerView que se va llenando con los datos obtenidos desde una base de datos de Firebase, descrita más adelante. Los datos llegan al RecyclerView a través de un componente adaptador que hereda sus caracteristicas del adaptador base que se provee por el sistema, se almacenan los datos en el adaptador y se cargan directamente al RecyclerView empleando un ViewHolder que crea un componente diseñado previamente (Tarjeta) que es el producto final que el usuario visualiza en su pantalla. Acceder al mismo dispara una nueva actividad con los datos de la receta seleccionada.

###### En Progreso: Eliminacion de recetas

##### Calendario

En progreso...

##### Social

Permite acceder y leer comentarios de otros usuarios sobre su manejo de la aplicación y su experiencia en el uso de las recetas. 
La actividad principal se compone de un RecyclerView que se va llenando con los datos obtenidos desde una base de datos de Firebase, descrita más adelante. Los datos llegan al RecyclerView a través de un componente adaptador que hereda sus caracteristicas del adaptador base que se provee por el sistema, se almacenan los datos en el adaptador y se cargan directamente al RecyclerView empleando un ViewHolder que crea un componente diseñado previamente (Tarjeta) que es el producto final que el usuario visualiza en su pantalla.

###### En Progreso: Eliminacion de comentarios

## Firebase

La aplicación hace uso de varios servicios de Firebase con el fin de reducir la cantidad de carga que se tiene en el desarrollo. 
Se emplean dos servicios principales nombrados a continuación empleando el plan Spark, para no incurrir en costos de mantenimiento de 
la app. 

### Firebase FireStore

El servicio de almacenamiento de objetos, similar al prestado por Amazon en S3, se emplea para el almacenamiento de las imagenes correspondientes
a cada receta. Una vez subida a cada imagen se le asigna un URL único al cuál se accede cuando se renderizan los componentes de receta dentro de la
aplicación. 

### Firebase Realtime Database

Se emplea el servicio de base de datos en tiempo real. El servicio presta acceso a una base de datos NoSQL organizada a través de documentos. 
Se emplean dos 'tablas' principales para el funcionamiento:

Una tabla 'Recetas' que contiene dentro de si el almacenamiento de los datos correspondientes a cada receta añadida por un usuario, contiene 
campos que incluyen el titulo de la receta, los pasos, el autor y una referencia a la ubicación en FireStore de la imagen subida por el usuario.

Adicionalmente, se emplea una tabla 'Comentarios' donde se almacenan los comentarios añadidos por los usuarios con el proposito de informar a otros
sus experiencias, se almacenan los datos correspondientes a un objeto comentario que incluyen, título, contenido y autor del mismo. 

### Firebase Auth

El servicio se implementó con la base de acceso a la aplicación a través del registro con correo electrónico. En un futuro es posible implementar el 
acceso al servicio usando accesos de terceros como Facebook y Twitter. Con el servicio de autenticación se facilita la creación y gestión de perfiles de 
usuario de la aplicación.

## Empleo de características de seguridad del sistema operativo Android

El sistema operativo Android corre cada aplicación en un entorno aislado único para si misma, de forma que las aplicaciones requieren IDs, únicos para poder acceder a los servicios del sistema. En este caso, dentro del manifesto de la aplicación se solicita directamente al sistema el permiso de acceso a conexión a internet para el sistema de logs de usuarios y de gestión de base de datos. Además de solicitar acceso para entrar al explorador de archivos del sistema cuando el usuario así lo requiera. El uso de los permisos se reduce a 
actividades específicas para evitar la posibilidad de abrir fallas de seguridad y vulnerabilidades del sistema.


## Diseño

Se siguen las guías generales de diseño para Android, establecidas por Google, entre ellas el manejo de componentes anclados a las ventanas, tamaños de texto variables según el tamaño del dispositivo, medidas de colores de contrastes para mejor accesibilidad. Sin embargo, se abstiene del uso de la barra estándar de android y se opta por un menú de interacción sin la misma para maximizar el uso de la pantalla del usuario para el render de componentes.

## Uso de Recursos String y de Imagen

Se siguen las normas estandar de inclusión de recursos de string con el propósito de facilitar la traducción y actualización de los recursos de texto existentes dentro de la aplicación. Sin embargo, las recetas no utilizan estos recursos puesto que son subidas en un único idioma que es español.

## Depuración

Para la detección y analisis de fallos, en su mayoría NullPointerExceptions se utiliza el depurador incluido en Android Studio y se emplea un dispositivo conectado directamente a la máquina desde la cual se despliega la aplicación, hasta el momento se ha hecho uso del depurador general y del depurador anclado a actividades específicas.

## Snapshots
![alt text](https://github.com/joviedog/QuarantApp/blob/master/QuarantAppSnapshot%20(1).jpeg "Inicio De Sesion")

![alt text](https://github.com/joviedog/QuarantApp/blob/master/QuarantAppSnapshot%20(2).jpeg)

![alt text](https://github.com/joviedog/QuarantApp/blob/master/QuarantAppSnapshot%20(3).jpeg)

![alt text](https://github.com/joviedog/QuarantApp/blob/master/QuarantAppSnapshot%20(4).jpeg)

![alt text](https://github.com/joviedog/QuarantApp/blob/master/QuarantAppSnapshot%20(5).jpeg)
