FROM openjdk:17-jdk-alpine
COPY target/app-v1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

