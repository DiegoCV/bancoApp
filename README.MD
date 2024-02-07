## Banco App
Aplicacion banco para el manejo de clientes y registros de movimientos.

### Get started

-   Docker version 23.0.2 or later.
-   Java 8
-   SpringBoot version 2.7.7
-   Postgresql version 12.2
-   RabbitMQ 3

### Aplicacion java con spring boot, 

Estructura del proyecto:
```
.
├── banco_db
│   └── BaseDatos.sql
├── banco_doc
│   └── ...
├── cuentas_app
│   ├── Dockerfile
│   └── ...
├── personas_app
│   ├── Dockerfile
│   └── ...
├── .env
├── docker-compose.yaml
└── README.md
```

El archivo docker-compose define dos aplicaciones `personas_app` y `cuentas_app` cada una de ellas corriendo en el puerto **8080** y **8081** correspondientemente. Adicional, se despliega un servicio de **rabbitMQ** para establecer las colas de comunicación entre los dos micros y por ultimo se levanta un servicio **postgresql** en el puerto **5432**.

En la sección **banco_doc** encontrará el modelo de base de datos junto con la coleccion postman para el consumo de las apis.

El esquema de la base de datos se construira de forma automatica utilizando el scritp BaseDatos.sql

En el archivo .env se definen el valor de las varibales usadas para la base de datos
```
DB=BaseDatos
POSTGRESUSER=banco
POSTGRESPASSWORD=banco
```

## Deploy with docker compose

```
$ docker compose up -d
```

## Postman 
Dentro de la carpeta de banco_doc se puede encontrar el postman con las apis habilitadas para funcionar.

A continuación un resumen de las operaciones:
-   cliente
```
-   crear cliente
-   consultar cliente
-   actualizar cliente
-   eliminar cliente
```
-   cuentas

```
-   crear cuenta
-   consultar cuenta
-   actualizar cuenta
-   eliminar cuenta
```
- movimiento (retiro ó deposito)

```
-   crear movimiento
-   consultar movimiento
```
-   reporte
```
-   generar reporte
```