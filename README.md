# Welcome to Swagger Example!

This application is under construction right now and is not complete!

There are several aspects which are discussed in this document.

* generating the Swagger API specification with springfox
* using a Swagger UI which is built in the application
* using the Swagger UI separately
* generating static REST API documentation

## Starting the application

To start the application clone the project. It comes with gradlew wrapper so the only thing that should be executed from within the project directory is:

`gradlew bootRun`

This will make the app available at [http://localhost:8080](http://localhost:8080)

## Authentication

The REST API is secured with Basic Authentication. The application has one built in user with name `lucho` and password `test`. It operates with H2 inmemory database.

# Exploring the API with the built in Swagger UI client

Start the application. The Swagger UI is exposed at the following endpoint [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

This endpoint is normally not available in production system thus the application is configured to expose it only when the current active profile is `dev`. The application is by default started with this profile.
