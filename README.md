# QuarantApp
Recetas de economía sustentable

## Programacion

El desarrollo de la aplicación se realiza en Java, utilizando el modelo de programación orientada a objetos. Para el desarrollo se emplea Android Studio como editor base y como dispositivos principales de pruebas telefonos Android conectados directamente a la máquina (No se han utilizado emuladores por el alto consumo de recursos que involucran los mismos).


###### Nota: La clonación completa de este repositorio puede producir problemas a la hora de correr. Si decide realizar una copia recuerde hacer los ajustes pertinentes para evitar fallos en su sistema.

El usuario tiene acceso a tres categorías principales.

##### Recetas

Puede acceder a las recetas que han subido otros usuarios, y añadir sus recetas propias. 
La actividad principal se compone de un RecyclerView que se va llenando con los datos obtenidos desde una base de datos de Firebase, descrita más adelante. Los datos llegan al RecyclerView a través de un componente adaptador que hereda sus caracteristicas del adaptador base que se provee por el sistema, se almacenan los datos en el adaptador y se cargan directamente al RecyclerView empleando un ViewHolder que crea un componente diseñado previamente (Tarjeta) que es el producto final que el usuario visualiza en su pantalla. Acceder al mismo dispara una nueva actividad con los datos de la receta seleccionada.

###### En Progreso: Eliminacion de recetas
Funcionalidad añadida con éxito. Ahora un usuario puede eliminar recetas que el mismo añadió, usuarios ajenos al creador de la receta no pueden eliminar recetas que no sean de su autoría.

##### Calendario

Permite acceder a un sistema para la programación de notificaciones relativas a una receta, el usuario puede seleccionar la fecha y hora, en que desea recibir la notificación relativa a sus recetas. La actividad principal se compone de 2 botones y dos labels. Cada botón dispara un fragmento independiente que actua como un selector nativo de la hora y de la fecha respectivamente. Adicionalmente, el usuario tiene la posibilidad de cancelar la notificación que acaba de programar en caso de cometer un error. Finalmente, la aplicación incorpora los estándares de notificaciones implementados desde la versión 8.0 del sistema operativo Android, dónde las notificaciones se ven obligadas a operar a través de canales con el propósito de permitir al usuario desactivar o activar solo aquellas notificaciones que sean relevantes para el uso que le da a la aplicación. 

##### Social

Permite acceder y leer comentarios de otros usuarios sobre su manejo de la aplicación y su experiencia en el uso de las recetas. 
La actividad principal se compone de un RecyclerView que se va llenando con los datos obtenidos desde una base de datos de Firebase, descrita más adelante. Los datos llegan al RecyclerView a través de un componente adaptador que hereda sus caracteristicas del adaptador base que se provee por el sistema, se almacenan los datos en el adaptador y se cargan directamente al RecyclerView empleando un ViewHolder que crea un componente diseñado previamente (Tarjeta) que es el producto final que el usuario visualiza en su pantalla.


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

## Snapshots de las actividades de la aplicacion

###### Nota Importante: Todas las actividades desarrolladas hasta ahora emplean como plantilla base una actividad vacía. 

###### Pantalla de Landing

Se muestra al iniciar la aplicación, muestra dos botones principales que disparan las actividades correspondientes al inicio de sesión y el registro del usuario.

![alt text](https://github.com/joviedog/QuarantApp/blob/master/QuarantAppSnapshot%20(5).jpeg)

###### Inicio de Sesion

Se muestran los campos para el ingreso de los datos y un botón para disparar la siguiente actividad que permite al usuario acceder a la navegación principal.

![alt text](https://github.com/joviedog/QuarantApp/blob/master/QuarantAppSnapshot%20(1).jpeg "Inicio De Sesion")

###### Tablero Principal

Se cataloga como la pantalla principal de navegación de la aplicación, le brinda al usuario acceso a las 3 funcionalidades de la aplicación con facilidad. 

![alt text](https://github.com/joviedog/QuarantApp/blob/master/QuarantAppSnapshot%20(2).jpeg)

###### Social

Muestra los mensajes agregados por otros usuarios usando un RecyclerView que se llena con datos obtenidos de la base de datos de Firebase.

![alt text](https://github.com/joviedog/QuarantApp/blob/master/QuarantAppSnapshot%20(3).jpeg)

###### Recetas

Muestra las recetas agregadas por otros usuarios usando un RecyclerView que se llena con datos obtenidos de la base de datos de Firebase.

![alt text](https://github.com/joviedog/QuarantApp/blob/master/QuarantAppSnapshot%20(4).jpeg)

###### Interfaz de recordatorios

![alt text](https://github.com/joviedog/QuarantApp/blob/master/CalendarFunction%20(1).jpeg)

###### Notificaciones 

![alt text](https://github.com/joviedog/QuarantApp/blob/master/CalendarFunction%20(2).jpeg)

# Link de Descarga

Aplicativo

https://drive.google.com/file/d/1u5LxjAvRkOHYZdAY6-AdkksBLAjtmDe8/view?usp=sharing

Video

https://drive.google.com/file/d/1W4DaXMfhm-WLoEd50i194MLgwXWqA_Ix/view?usp=sharing

# Perspectivas

En el marco del desarrollo de la aplicación es posible abrir muchas perspectivas y posibilidades en términos de funcionalidades a las cuales se podría llegar, entre ellas se destacan:

1. Implementación de inicio de sesión con biométrico del telefono: Facilitaría al usuario el inicio de sesión evitando así que deba recordar una contraseña y agilizando el proceso de consulta de recetas.

2. Implementación de edición de comentarios: La edición de comentarios se plantea como una necesidad para aquellos usuarios que lleguen a cometer errores en términos de escritura o que deseen añadir cosas adicionales a su receta.

3. Implementación de iteracciones con API como Google Calendar: El uso de APIs como Google Calendar permitiría que el usuario pueda incorporar en una herramienta que ya usa constantemente las notificaciones y planear de forma más organizada en el largo plazo.

4. Implementación en otras plataformas a través de lenguajes nativos o frameworks multiplataforma.
