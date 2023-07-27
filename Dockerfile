FROM openjdk:17-jdk-slim-buster
MAINTAINER foodhub
COPY target/food-service.jar food-service.jar
ENTRYPOINT ["java","-jar","/food-service.jar"]