FROM maven:3.8.5-openjdk-17

WORKDIR /api-gateway

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn package -DskipTests

FROM openjdk:17-jdk

COPY /target/api-gateway-0.0.1-SNAPSHOT.jar /api-gateway/launch-api-gateway.jar

ENTRYPOINT ["java","-jar","/api-gateway/launch-api-gateway.jar"]

EXPOSE 8765