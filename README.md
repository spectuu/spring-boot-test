# 💻 Prueba Práctica - Spring Boot 
  
Este repositorio contiene un proyecto de plantilla realizado con spring-boot, posee 2 endpoints de prueba y una entidad llamada User, donde actualmente solo es posible obtener una lista de usuarios. Usted deberá clonar y realizar ciertas modificaciones al proyecto enumeradas a continuación.  
  
- Modificar el archivo `application.yml` y cambiar los datos por los de su base de datos local.  
- Agregar un nuevo atributo al usuario llamado password (no hay necesidad de encriptar este atributo).  
- Hacer un CRUD completo, para agregar, obtener, modificar y remover a los usuarios. (Utilizar la entidad pre-existente User)  
- Hacer un endpoint que en base a una contraseña y un email enviado verifique que sea un registro valido de la base de datos y por consiguiente devuelva `true` en caso de que exista un usuario con ese email y contraseña o `false` en caso de que no.  
- Crear un repositorio en github, gitlab o cualquier plataforma git y subir todo su proyecto en dicha plataforma.  
  
Recomendaciones:  
  
- Asegúrese de tener [MySQL](https://www.mysql.com/downloads/) o en caso de que quiera utilizar otra base de datos configurar el proyecto para soportarla.  
- Utilice su IDE de preferencia.  
- Utilice [Postman](https://www.postman.com/downloads/) o alguna herramienta similar para que pueda realizar pruebas.  
- No agregue cosas que no se piden o no son necesarias, puesto que se pretende evaluar tiempo de implementación y es mejor invertir el tiempo en las modificaciones enumeradas.

Tenga en cuenta que:

- El proyecto utiliza Maven junto con varias librerías adicionales.
- El proyecto utiliza la librería [Lombok](https://projectlombok.org/setup/overview), por tanto si utiliza una IDE que no lo soporte de manera nativa, usted deberá configurar el pre-procesador de su IDE para soportar los errores de sintaxis de lombok, para ello puede ver una guía de como instalar lombok en cada IDE por medio del link al principio de este numeral.