FROM openjdk:21-jdk-slim
WORKDIR /app
ARG JAR_FILE=target/smartpoke.api-0.0.1
COPY ${JAR_FILE} smartpoke.api-0.0.1
EXPOSE 8080
CMD ["java", "-jar", "smartpoke.api-0.0.1.jar"]