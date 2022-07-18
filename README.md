## Overview

This is the technical test from Martin Maturano.

This is a Spring Boot application written in Java. Maven is used as the build tool.
The java JDK used is Amazon Corretto 11.

## Endpoints documentation
- Swagger exposes documentation about the endpoint of the application.

Running in localhost, the documentation can be found at:

- http://localhost:8080/swagger-ui.html UI to visualize and interact with the API.
- http://localhost:8080/v3/api-docs: API docs in JSON format.

## To test application
To test the endpoints. First we have to create a token with http://localhost:8080/auth endpoint.
- user: admin
- password: 123456

Then we will have to send the token as bearer token.

## Docker
To run application with docker we have to run:
- docker-compose up --build
