FROM openjdk:21-jdk-slim
WORKDIR /app
ARG JAR_FILE=target/smartpoke_api-0.0.1.jar
COPY ${JAR_FILE} smartpoke_api.jar
EXPOSE 8080
CMD ["java", "-jar", "smartpoke.api-0.0.1.jar"]