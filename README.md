# Table of contents

- [Welcome to the Swagger sample project!](#welcome-to-the-swagger-sample-project)
  * [Overview](#overview)
  * [Starting the application](#starting-the-application)
  * [Exploring the application (Eclipse)](#exploring-the-application-eclipse)
  * [Authentication](#authentication)
- [Exploring the REST API with a built in Swagger UI client](#exploring-the-rest-api-with-a-built-in-swagger-ui-client)
- [Exploring the REST API with stand alone Swagger UI](#exploring-the-rest-api-with-stand-alone-swagger-ui)
- [Generate static HTML documentation](#generate-static-html-documentation)
- [Automation](#automation)
  * [Generating Swagger specification with gradle at build time](#generating-swagger-specification-with-gradle-at-build-time)
- [The Big Picture](#the-big-picture)
- [Feedback](#feedback)



# Welcome to the Swagger sample project!

___
This application as well as the documentation is under construction right now and is fully complete!
___


## Overview

This sample application focuses on swagger and its usage in Spring porjects. There are several usages of Swagger discussed here.

* generating the Swagger API specification with springfox
* using a Swagger UI which is built in the application
* using the Swagger UI stand alone
* generating static REST API documentation
* generating swagger build artifacts

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

# Exploring the REST API with a built in Swagger UI client

Start the application. This application comes with a built in Swagger UI client. It is exposed at the following endpoint [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

This endpoint is normally not available in production system thus the application is configured to expose it only when the current active profile is `dev`. The application is by default started with this profile. The UI should look similar to the one below (at the time of writing).

![image](https://cloud.githubusercontent.com/assets/10339738/26170556/89282b28-3b4a-11e7-8226-d9ff9e30f144.png)

# Exploring the REST API with stand alone Swagger UI

Start the application. It is neither required nor even recommended to bundle Swagger UI with the application the standalone client might be used. Clone the [Sagger UI](https://github.com/swagger-api/swagger-ui.git). This may take some time. Change to `swagger-ui\dist` and open `index.html` within your browser. In the URL input at the top of the page enter the URL of the Swagger Specification exposed by the application. This should be `http://localhost:8080/v2/api-docs`. Click explore.

# Generate static HTML documentation

Start the application. Download a binary distribution of swagger codegen CLI, for example from [Maven](http://central.maven.org/maven2/io/swagger/swagger-codegen-cli/2.2.2/swagger-codegen-cli-2.2.2.jar). Note: check if newer version is available. Place the jar file in a directory of your choice and navigate to this directory. If the java environment is step correctly the following could be executed:

```
java -jar swagger-codegen-cli-2.2.2.jar generate -l html2 -i http://localhost:8080/v2/api-docs -o docs
```

The command will generate static HTML documentation and store it in the `docs` folder. All this is not as good as Swagger UI and looks like this at the time of writing: 

![image](https://cloud.githubusercontent.com/assets/10339738/26173363/35f9f8d2-3b54-11e7-93df-e0ca9d144b07.png)


# Automation

Everything about `springfox` is nice but in order to generate a swagger specification the server needs to be running. This may not be perfect for CI. The swagger specification may be reqired in build time when there is no running server.

## Generating Swagger specification with gradle at build time

At the time of writing there is no very good support for gradle. Maven is the favoured build system. There is a nice [maven plugin](https://github.com/kongchen/swagger-maven-plugin). This project uses a [gradle port](https://github.com/gigaSproule/swagger-gradle-plugin) of the plugin. To generate swagger specification execute:

```
gradlew build 
gradlew swagger
```

The swagger spec is available under the project root in `\generated\swagger-json`. The swagger spec may be used in various ways, e.g. it may be even tried out in the [online swagger editor](http://editor.swagger.io/). It can be deployed as a release artifact.

## Sample usage of the swagger spec

The project comes with a sample gradle plugin for `swagger2markup` which is able to generate Ascii Doc from the swagger specification. To see it in action execute:

```
gradlew build 
gradlew swagger
gradlew convertSwagger2markup -b swagger2makup.gradle
```

The output will be generated in under `generated/asciidoc`.

# The Big Picture

TODO

# Feedback

[Ideas?](https://github.com/luchob/swagger-example/pulls) [Issues?](https://github.com/luchob/swagger-example/issues)
