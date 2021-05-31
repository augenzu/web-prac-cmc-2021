FROM openjdk:15-jdk-alpine

COPY /target/*.jar /app.jar

ENV HOST 127.0.0.1  # localhost
ENV PORT 8080       # standard tomcat port

ENTRYPOINT ["java","-jar", "/app.jar"]