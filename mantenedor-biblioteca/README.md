# PRUEBA PRÁCTICA GENERAL
***
- El alumno deberá crear un proyecto starter spring boot
- Configurar el proyecto para thymeleaf (crear 1 htmls de Login y y modificar el Welcome logout
- Crear una clase filtro con los usuarios en memoria y los atributos para segurizar las APIS
- Modificar una clase RestController con 2 métodos: 
 - Método Login si es correcto devuelve HTML welcome
 - Método Base sin URL debe devolver DTO’s desde BD en formato json al welcome
- Segurizar todos los métodos del proyecto anterior asegurándose que esté autenticado para acceder, con excepción del Login
- Se debe generar 2 entidades Libro y Autor con sus respectivos repositorios
- Se debe generar 1 interfaz y 1 implementación de tipo DAO que acceda a las entidades a través del repositorio Autowired y en la clase dao se debe transformar a DTO los valores de las entidades
- Se debe generar una clase service que permita separar la clase controler de la DAO y acceda a la interfaz del DAO Autowired
- Incluir las librerías necesarias en el POM xml para ejecutar las pruebas