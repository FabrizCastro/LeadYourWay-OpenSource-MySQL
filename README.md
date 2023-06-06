# LeadYourWay-OpenSource-MySQL

Este proyecto es una plataforma similar a Airbnb pero enfocada en el alquiler de bicicletas. Permite a los usuarios encontrar bicicletas disponibles para alquilar en diferentes ubicaciones.

## Introducción

LeadYourWay-OpenSource-MySQL es una aplicación web desarrollada utilizando el framework Spring. Proporciona una interfaz intuitiva y fácil de usar para que los usuarios puedan buscar y alquilar bicicletas de forma segura y conveniente.

El objetivo principal de esta plataforma es facilitar la conexión entre los propietarios de bicicletas y los usuarios interesados en alquilarlas. Los usuarios pueden explorar una variedad de bicicletas disponibles, ver detalles y precios, realizar reservas y coordinar el proceso de recogida y devolución.

## Endpoints

A continuación se muestra una lista de los diferentes endpoints disponibles en la API para interactuar con las entidades principales del sistema.

### User

- **GET** /api/user - Obtiene todos los usuarios registrados.
- **GET** /api/user/{id} - Obtiene un usuario específico por su ID.
- **GET** /api/user/filterByEmail - Obtiene los usuarios por su correo.
- **POST** /api/user - Crea un nuevo usuario.
- **PUT** /api/user/{id} - Actualiza la información de un usuario existente.
- **DELETE** /api/user/{id} - Elimina un usuario por su ID.

### Card

- **GET** /api/cards - Obtiene todas las tarjetas asociadas a los usuarios.
- **GET** /api/cards/{id} - Obtiene una tarjeta específica por su ID.
- **GET** /api/cards/user/{id} - Obtiene todas las tarjetas de un user por el ID del.
- **POST** /api/cards/{userId} - Crea una nueva tarjeta asociada a un usuario.
- **PUT** /api/cards/{id} - Actualiza la información de una tarjeta existente.
- **DELETE** /api/cards/{id} - Elimina una tarjeta por su ID.

### Bike

- **GET** /api/bike - Obtiene todas las bicicletas disponibles para alquilar.
- **GET** /api/bike/{id} - Obtiene información detallada de una bicicleta específica por su ID.
- **GET** /api/bike/filterByBicycleName - Obtiene información de las bicicleta específica por un nombre.
- **POST** /api/bike/{userId} - Crea una nueva bicicleta para ser alquilada.
- **PUT** /api/bike/{id} - Actualiza la información de una bicicleta existente.
- **DELETE** /api/bike/{id} - Elimina una bicicleta por su ID.
