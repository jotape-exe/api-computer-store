# Spring Boot API Usage Guide for Computer Shop

<a alt="Java">
    <img src="https://img.shields.io/badge/Java-v17-red.svg" />
</a>
<a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v3.0.5-brightgreen.svg" />
</a>
<a alt="Gradle">
    <img src="https://img.shields.io/badge/Gradle-v8.0.2-blue.svg" />
</a>

This document provides instructions on how to use the Spring Boot API to register customers, products, and orders in a computer store. The API was developed in Java and also makes requests to the external API ```https://viacep.com.br``` to register a customer with an existing Brazilian address. It is also worth noting that the MySQL database. Additionally, the API has documentation for the Swagger-ui interface, which can be used to test endpoints.

## Configuration

Before you begin, make sure you have MySQL installed and configured correctly. Then, create a database in MySQL and update the `application.properties` file settings with the database access information, as shown in the example below:

```
#Database Config
spring.datasource.url=jdbc:mysql://localhost:3306/<DB_NAME>?createDatabaseIfNotExist=true
spring.datasource.username=<MYSQL_USERNAME>
spring.datasource.password=<MYSQL_PASSWORD>
```

Replace <DB_NAME>, <DB_USERNAME> and <DB_PASSWORD> with the correct information.

## Testing the endpoints

After running the application, go to 
```
http://localhost:8080/swagger-ui/index.html#/ 
```
and voilá! You will have access to Swagger with the fully documented api. From Schemas to the endpoints of each controller, Swagger does it without you having to use a web client.

## Database Modeling

<img src="DatabaseModeling.png">

### Relationships between entities

- Client/ Address(One to Many)
- Order/ Product(Many to Many) 

To follow the relational model, _Many to Many_ relationships cannot exist. So the **OrderProduct** entity solves the problem

- Order/ OrderProduct(One to Many)
- Product/ OrderProduct(One to Many)

## Spring Boot Architecture

<img src="springboot.png">