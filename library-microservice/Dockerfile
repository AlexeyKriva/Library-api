FROM maven:3.8.5-openjdk-17

WORKDIR /library

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean
RUN mvn package -DskipTests

FROM openjdk:17-jdk

COPY /target/library-microservice-0.0.1-SNAPSHOT.jar /library/launch-library.jar

ENTRYPOINT ["java","-jar","/library/launch-library.jar"]

EXPOSE 33336