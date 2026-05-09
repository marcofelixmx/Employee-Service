# Ejercicio de evaluación - Invex

Este es un microservicio desarrollado para la Evaluación Técnica.

## Arquitectura
El proyecto sigue los principios de **Clean Architecture** (Arquitectura Hexagonal) combinados con el patrón **CQRS** (Command Query Responsibility Segregation).

- **Domain (Dominio)**: Lógica de negocio principal y entidades puras.
- **Application (Aplicación)**: Handlers de CQRS (Comandos y Consultas).
- **Infrastructure (Infraestructura)**: Adaptadores de persistencia (Spring Data JPA con Oracle) y Mappers.
- **API**: Controladores REST y DTOs de entrada/salida.

## Stack Tecnológico
- **Java 17**
- **Spring Boot 2.7.18**
- **Oracle Database** (vía Docker) 
- **MapStruct** para el mapeo eficiente de objetos.
- **Lombok** para reducir el código repetitivo.
- **JUnit 5 & Mockito** para pruebas unitarias.
- **Docker & Docker Compose** para la contenedorización.
- **SpringDoc OpenAPI** para la documentación con Swagger.

## Prerrequisitos
- Docker y Docker Compose
- JDK 17 (si se ejecuta localmente sin Docker)
- Maven (si se ejecuta localmente sin Docker)

## Cómo Ejecutar el Proyecto

### Usando Docker Compose 
Esto levantará tanto la base de datos Oracle como el Servicio de Empleados.

```bash
docker-compose up --build
```

El servicio estará disponible en `http://localhost:8080`.

### Ejecución Local
Por defecto, la aplicación utiliza la base de datos H2 en memoria si no se proporcionan variables de entorno de Oracle.

```bash
mvn spring-boot:run
```

## Documentación de la API
Una vez que la aplicación esté en funcionamiento, puedes acceder a la interfaz de Swagger UI en:
`http://localhost:8080/swagger-ui.html`

## Endpoints Principales
- `GET /api/v1/employees`: Lista todos los empleados.
- `GET /api/v1/employees/{id}`: Obtiene el detalle de un empleado por su ID.
- `POST /api/v1/employees`: Crea uno o varios empleados.
- `PUT /api/v1/employees/{id}`: Actualiza los datos de un empleado.
- `DELETE /api/v1/employees/{id}`: Elimina un empleado.
- `GET /api/v1/employees/search?name={nombre}`: Busca empleados por nombre.

## Monitoreo y Salud (Spring Boot Actuator)
El microservicio incluye **Spring Boot Actuator** para el monitoreo proactivo:
- **Health Check**: `http://localhost:8080/actuator/health`
  - Proporciona un estado detallado de la aplicación, incluyendo la conectividad con la base de datos Oracle y el espacio en disco.
- **Métricas**: `http://localhost:8080/actuator/metrics`
  - Expone métricas de la JVM, uso de CPU y pool de conexiones.

## Evidencias
- [Lista todos los empleados](https://github.com/marcofelixmx/Employee-Service/tree/main/Evidencias/Consultar_todos_los_empleados.PNG)
- [Obtiene el detalle de un empleado por su ID](https://github.com/marcofelixmx/Employee-Service/tree/main/Evidencias/Consultar_empleado_por_Id.PNG)
- [Crea un empleado](https://github.com/marcofelixmx/Employee-Service/tree/main/Evidencias/Alta_un_empleado.PNG)
- [Crea  varios empleados](https://github.com/marcofelixmx/Employee-Service/tree/main/Evidencias/Alta_varios_empleados.PNG)
- [Actualiza los datos de un empleado](https://github.com/marcofelixmx/Employee-Service/tree/main/Evidencias/Actualizar_empleado.PNG)
- [Elimina un empleado](https://github.com/marcofelixmx/Employee-Service/tree/main/Evidencias/Elimiar_Empleado.PNG) 
- [Busca empleados por nombre](https://github.com/marcofelixmx/Employee-Service/tree/main/Evidencias/Buscar_Empleado.PNG)



