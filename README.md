# Microservice library application

## API Documentation

The API specification can be found in the [API documentation](docs/api/OpenApiDescriptionUsingSwagger.yaml).

## Instructions for starting the project from the terminal

From the project root directory, run the following:

1) Clean up previous build files:
   mvn clean package
2) Install project:
   mvn clean install
3) Start the eureka server:
   java -jar eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar
4) Start the auth microservice:
   java -jar auth-microservice/target/auth-microservice-0.0.1-SNAPSHOT.jar
5) Start the book microservice:
   java -jar book-microservice/target/book-microservice-0.0.1-SNAPSHOT.jar
6) Start the api gateway:
   java -jar api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar

