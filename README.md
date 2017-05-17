# Welcome to the Swagger sample project!

___
This application as well as the documentation is under construction right now and is not complete!
___

There are several usages of Swagger discussed here.

* generating the Swagger API specification with springfox
* using a Swagger UI which is built in the application
* using the Swagger UI stand alone
* generating static REST API documentation

## Starting the application

To start the application clone the project. It comes with gradlew wrapper so the only thing that should be executed from within the project directory is:

`gradlew bootRun`

This will start a server locally at port 8080.

## Exploring the application (Eclipse)

The application may be imported in Eclipse as an existing project. To do so from the project directory execute:

`gradlew eclipse`

Open eclipse and import the project as existing java project.

## Authentication

The REST API is currently secured with Basic Authentication. The application has one built in user with name `lucho` and password `test`. 

# Exploring the REST API with the built in Swagger UI client

Start the application. The Swagger UI is exposed at the following endpoint [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

This endpoint is normally not available in production system thus the application is configured to expose it only when the current active profile is `dev`. The application is by default started with this profile.

# Exploring the REST API with stand alone Swagger UI

TODO


