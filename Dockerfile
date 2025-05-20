FROM openjdk:23-ea-17-jdk
WORKDIR /app
COPY target/payment-status-microservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]