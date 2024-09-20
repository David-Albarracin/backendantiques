# Spring Boot eCommerce API

Este proyecto implementa una API para un sistema de comercio electrónico utilizando **Spring Boot**, con características como JPA, **Spring Security** y controladores RESTful conectados a una base de datos **MySQL**. La arquitectura sigue un enfoque orientado al dominio (DDD).

## Tecnologías utilizadas

- **Spring Boot**
- **JPA (Java Persistence API)**
- **Spring Security**
- **MySQL**
- **RESTful API**

## Diagrama entidad-relación

![Diagrama entidad-relación](./sql/diagrama.png)

## Comandos DDL para construir la estructura de la base de datos

Aquí se incluyen los comandos para crear las tablas de la base de datos en **MySQL**. Los atributos de cada tabla se describen en los archivos SQL proporcionados:

```sql

```

## Endpoints

Cada uno de los módulos del sistema cuenta con su propio conjunto de endpoints que permiten **agregar**, **actualizar**, **obtener** y **eliminar** registros de la base de datos. A continuación, se describe la estructura estándar de los endpoints para cada módulo.

### Estructura de los Endpoints

- **POST**: Para agregar un nuevo recurso.
- **PUT**: Para actualizar un recurso existente.
- **GET**: Para obtener todos los recursos.
- **GET/{id}**: Para obtener un recurso específico por su ID.
- **DELETE/{id}**: Para eliminar un recurso específico por su ID.

### Ejemplos de Endpoints

#### Antigüedades
- `POST /antiguedades`: Agregar una nueva antigüedad.
- `PUT /antiguedades`: Actualizar una antigüedad existente.
- `GET /antiguedades`: Obtener todas las antigüedades.
- `GET /antiguedades/{id}`: Obtener una antigüedad específica por su ID.
- `DELETE /antiguedades/{id}`: Eliminar una antigüedad específica por su ID.

#### Categorías
- `POST /categorias`: Agregar una nueva categoría.
- `PUT /categorias`: Actualizar una categoría existente.
- `GET /categorias`: Obtener todas las categorías.
- `GET /categorias/{id}`: Obtener una categoría específica por su ID.
- `DELETE /categorias/{id}`: Eliminar una categoría específica por su ID.

#### Ciudades
- `POST /ciudades`: Agregar una nueva ciudad.
- `PUT /ciudades`: Actualizar una ciudad existente.
- `GET /ciudades`: Obtener todas las ciudades.
- `GET /ciudades/{id}`: Obtener una ciudad específica por su ID.
- `DELETE /ciudades/{id}`: Eliminar una ciudad específica por su ID.

#### Coleccionistas
- `POST /coleccionistas`: Agregar un nuevo coleccionista.
- `PUT /coleccionistas`: Actualizar un coleccionista existente.
- `GET /coleccionistas`: Obtener todos los coleccionistas.
- `GET /coleccionistas/{id}`: Obtener un coleccionista específico por su ID.
- `DELETE /coleccionistas/{id}`: Eliminar un coleccionista específico por su ID.

#### Despachos
- `POST /despachos`: Agregar un nuevo despacho.
- `PUT /despachos`: Actualizar un despacho existente.
- `GET /despachos`: Obtener todos los despachos.
- `GET /despachos/{id}`: Obtener un despacho específico por su ID.
- `DELETE /despachos/{id}`: Eliminar un despacho específico por su ID.

#### Transacciones
- `POST /transacciones`: Agregar una nueva transacción.
- `PUT /transacciones`: Actualizar una transacción existente.
- `GET /transacciones`: Obtener todas las transacciones.
- `GET /transacciones/{id}`: Obtener una transacción específica por su ID.
- `DELETE /transacciones/{id}`: Eliminar una transacción específica por su ID.

### Otros Módulos

Cada módulo adicional sigue el mismo esquema para los endpoints. Solo cambia el nombre del módulo en el endpoint. Por ejemplo, para el módulo `empleados`, los endpoints serían:

- `POST /empleados`
- `PUT /empleados`
- `GET /empleados`
- `GET /empleados/{id}`
- `DELETE /empleados/{id}`

## Autenticación

Esta API utiliza **Spring Security** para la autenticación y autorización de usuarios. Los detalles de implementación de seguridad se encuentran en los archivos de configuración de Spring.

