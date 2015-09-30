# rabbit-cloud

## About this Application
This is a very Basic Application built using Spring Boot. The application exposes two HTTP REST endpoints:
* To publish a message to a queue
* To receive a message from the queue

This Application has code that uses Spring Profiles. Spring Profiles provide a way to segregate parts of your application configuration and make it only available in certain environments. The application has been configured to run with a 
* Dev Profile -- to run on a Development Environment
* Cloud Profile -- to run on Pivotal Cloud Foundry

## Spring Boot
Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run". We take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss. Most Spring Boot applications need very little Spring configuration.
### Features
* Create stand-alone Spring applications
* Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files)
* Provide opinionated 'starter' POMs to simplify your Maven configuration
* Automatically configure Spring whenever possible
* Provide production-ready features such as metrics, health checks and externalized configuration
* Absolutely no code generation and no requirement for XML configuration

To run these examples,

`git clone`


Build using Maven

`mvn package`


Run the application on a Development environment

`java -jar --spring.profiles.active=dev <jar filename>`


To publish a message on localhost, in a browser, type

`localhost:8080/publish?message=<message>`


To receive messages on localhost, in a browser, type

`localhost:8080/receive`


Run the application on Pivotal Cloud Foundry, first push the application to Pivotal Cloud Foundry

`cf push`
