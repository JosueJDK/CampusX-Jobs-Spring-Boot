# Api Rest Spring Hexagonal Architecture

Descripción concisa de tu API y su funcionalidad.

## Requisitos

- [Java](https://www.java.com/) (versión 17)
- [Spring Boot](https://spring.io/projects/spring-boot) (versión 3.1.2)
- [PostgreSQL](https://www.postgresql.org/)
- [JWT](#) (versión 0.9.2)

## Configuración

1. Clona el repositorio:

       git clone https://github.com/JosueJDK/CampusX-Jobs-Spring-Boot.git

2. Configura la base de datos PostgreSQL en src/main/resources/application.properties:

   **application.properties**

        spring.datasource.url=jdbc:postgresql://localhost:5432/tu_basedatos
        spring.datasource.username=tu_usuario
        spring.datasource.password=tu_contraseña
        spring.jpa.hibernate.ddl-auto=update

3. Ejecuta la aplicación:

       ./mvnw spring-boot:run

## Endpoints
**Auth**

      ### Registrarse en la aplicación
      POST /api/v1/auth/register

      ### Iniciar sesión
        POST /api/v1/auth/login

      ### Cerrar Session
        GET /api/v1/auth/logout/{user_id}

      ### Refrescar AccessToken
         POST /api/v1/auth/refresh-token

      ### Comprobar validez del token
         POST /api/v1/auth/check-available-token

      ### Verificar disponibilidad de correo electrónico.
        GET /api/v1/auth/check-available-email/{email}

© 2023 | JouCode
