FROM maven:3.8.5-openjdk-17

WORKDIR /book

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean
RUN mvn package -DskipTests

FROM openjdk:17-jdk

COPY /target/book-microservice-0.0.1-SNAPSHOT.jar /book/launch-book.jar

ENTRYPOINT ["java","-jar","/book/launch-book.jar"]

EXPOSE 33334