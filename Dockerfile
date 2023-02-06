FROM openjdk:8-jre-slim
EXPOSE 8080
COPY target/ClassCreator-0.0.1-SNAPSHOT.jar /app/ClassCreator.jar
