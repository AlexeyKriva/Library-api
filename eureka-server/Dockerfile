FROM maven:3.8.5-openjdk-17

WORKDIR /eureka-server

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean
RUN mvn package -DskipTests

FROM openjdk:17-jdk

COPY /target/eureka-server-3.3.2.jar /eureka-server/launch-eureka.jar

ENTRYPOINT ["java","-jar","/eureka-server/launch-eureka.jar"]

EXPOSE 8761