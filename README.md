# Ada_UD3_1gui

####################################################ESP
Pasos para ejecutar la aplicacion sin problemas:

-1-----
	Ejecuta el archivo "IES.SQL" , este archivo contiene la base de datos y algunos ejemplos.
	Este paso es fundamental ya que sin la base de datos el progrma no funcionara.

-2-----
	Situese en la raiz de la carpeta y ejecute el archivo .jar
	"java -jar ies.jar"

-3-----
	La primera vez que habra el program le pedira las credenciales de la conexion
	al sistema gestor, estas credenciales las debera conocer ust
	Tras esto no sera necesario volver a introdurcir dichas credenciales.
	...IMPORTANTE.....
	Si desea eliminar estas credenciales puede hacerlo en el archivo contenido en la ruta:
	
	src/main/resources/META-INF/hibernate.cfg.xml
	Esta accion no es recomendale y la desaconsejo

-4-----
	Una vez en el programa podra:	
		-Visualizar todos los datos de la base de datos, mediante la lista en la parte superior izquierda
		-Filtrar los datos de su interes, mediante la lista , el campo de texto y el boton situados en la parte superior izquierda
		-Modificar datos seleccionados por medio de las opciones del menu File, ubicado en la parte superior izquierda
		-Añadir nuevas entradas en la base de datos por medio del menu File
		-Eliminar las entradas seleccionadas por medio del menu File

####################################################ENG
Steps to run the application without problems: 

-1-----
Execute the file "IES.SQL", this file contains the database and some examples.
This step is fundamental since without the database the program will not work.

-2-----
Position yourself in the root of the folder and execute the .jar file
"java -jar ies.jar"

-3-----
The first time you open the program it will ask for the credentials of the connection
to the management system, these credentials should be known to you.
After this it will not be necessary to re-enter these credentials.
......IMPORTANT.....
If you want to delete these credentials you can do so in the file contained in the path:

src/main/resources/META-INF/hibernate.cfg.xml
This action is not recommended and I advise against it

-4-----
Once in the program you can:
	-View all the data in the database, through the list in the upper left
	-Filter the data of your interest, through the list, the text field and the button located in the upper left
	-Modify selected data through the options in the File menu, located in the upper left
	-Add new entries to the database through the File menu
	-Delete the selected entries through the File menu
