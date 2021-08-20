Vaccination Application Version 1.0


Overview

This application allows to manage vaccine inventory, through back-end that uses authorization and authentication methods.

Arquitecture

This back-end application is implemented in Java 11 and Spring Boot 2.5.3, it uses JWT to authenticate and authorize. It connects to a PostGreSQL Database, which can be created using the docker-compose.yml in the project root.

Installation

Once application starts, you need to run database.sql script so that initial data can be migrated to the database.

Documentation

You can find more detail about endpoint in https://app.swaggerhub.com/apis/pfss78/Vaccination/1.0.0
