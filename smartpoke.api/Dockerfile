FROM maven:3.9.5-openjdk-21 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install


FROM openjdk:21-jdk-slim
WORKDIR /app
ARG JAR_FILE=target/smartpoke_api-0.0.1.jar
COPY ${JAR_FILE} smartpoke_api.jar
EXPOSE 8080
CMD ["java", "-jar", "smartpoke.api-0.0.1.jar"]