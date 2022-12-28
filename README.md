# VW-Exercise
This is a volkswagen exercise project

# Design 

The design information can be found [here](./design%20diagrams/README.md).

# Frontend

## Development

When developing code for the frontend, the following tools are used:

- Code Editor: VS Code
- Browser: Chrome
- NodeJS with NPM - node version 12
- Angular 9

## NPM

It is recommended to install the global Angular package:

```
npm install -g @angular/cli
```

## Run the frontend

Go to frontend folder
> `cd frontend`

Now, you can run the project using `ng serve`. A dev server will start on `localhost:4200`.

## Test

use `ng test` command to run the tests.

# Backend Server (Restful API server)

Console application that manage the access to the database and the CRUD operations in the database.

## Development

The following tools were used for development.
- JDK 17 (Java Development Kit 17)
- IDE: IntelliJ 
- Java Framework: Springboot 3.0.0
- Maven 3.8.1
- H2-Database: Data storage

## Java Development Kit(JDK)
Use JDK 17.
Download and [install JDK](https://www.oracle.com/java/technologies/javase-downloads.html).

> Important: Make sure to [set up the environment variables](https://www.java.com/en/download/help/path.xml).

## IDE: IntelliJ

- [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows): Community, Education or Ultimate edition. Important: Use version 2020.1 or later!

## Maven
We use maven as a build tool
[Download](https://maven.apache.org/download.cgi) maven

## How to use

## Run the backend

Go to backend folder
> `cd backend`

Run `mvn spring-boot:run` 

or

Open IDE and start application from `ExerciseApplication.java`.

A dev server will start on `localhost:8080`

## Test

use `mvn test` command to run the tests.

## Database Data

When the backend application starts, the three tables will be created with data. The insert scripts used for the table can be found [here](./backend/src/main/resources/data.sql).

# Docker
It is used to dockerize the application. The best approach to start the application is to use the docker.

## Docker

Docker to run the application.
- [Download](https://docs.docker.com/docker-for-windows/install/) for Windows 
- [Download](https://docs.docker.com/docker-for-mac/install/) for Mac

> Docker version 20.10.12 and docker-compose version 1.29.2 used during development

## Run docker

Go to root folder where `docker-compose.yml` file exist.

Run `docker-compose up`. 

The frontend application starts at `localhost:4200` and backedn at `localhost:8080`.

## Issue while running docker 

> ERROR: Service 'backendserver' failed to build : Get "https://registry-1.docker.io/v2/": dial tcp: lookup registry-1.docker.io on 192.168.65.5:53: read udp 192.168.65.4:60859->192.168.65.5:53: i/o timeout

if above error is shown while running docker then run the following commands in the terminal.

export DOCKER_BUILDKIT=0

export COMPOSE_DOCKER_CLI_BUILD=0