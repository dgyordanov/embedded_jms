# Embedded ActiveMQ broker

## Overview
This is an embedded activeMQ broker wrapped in a spring boot application. it uses in memory storage for the messages. The broker could be easy started either using the spring boot fat jar or the gradle wrapper.

## Configuration
The configuration could be done via the standard spring boor application configuration: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html

Following properties are available:  
*broker.url*  
*broker.name*  
*broker.auth.username*  
*broker.auth.password*

## Run
Required JRE 8

This is a Spring boot application which could be run using gradle wrapper to using the fat jar generated in the build directory.

Run with gradle wrapper:
```shell
./gradlew bootRun
```

