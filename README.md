# Microservice library application

## API Documentation

The API specification can be found in the [API documentation](docs/api/OpenApiDescriptionUsingSwagger.yaml).

## Instructions for launching the project from the terminal

From the project root directory, run the following:

1) Clean up previous build files:
   'mvn clean'
2) Package project:
   'mvn package'
3) Launch the eureka server:
   'java -jar eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar'
4) Launch the auth microservice:
   'java -jar auth-microservice/target/auth-microservice-0.0.1-SNAPSHOT.jar'
5) Launch the book microservice:
   'java -jar book-microservice/target/book-microservice-0.0.1-SNAPSHOT.jar'
6) Launch the api gateway:
   'java -jar api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar'

## Instructions for launching the project from the docker-compose

From the project root directory, run the following command:

'docker-compose up --build'

