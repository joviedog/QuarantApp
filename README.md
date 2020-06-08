# QuarantApp
Recetas de economía sustentable


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

