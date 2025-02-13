FROM openjdk:23-jdk-slim
WORKDIR /app
COPY target/smth_backend-0.0.1-SNAPSHOT.jar /app/smth-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "smth-0.0.1-SNAPSHOT.jar"]