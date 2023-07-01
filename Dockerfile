FROM openjdk:17-ea-10-jdk-alpine
LABEL maintainer="Brody Gaudel https://github.com/BrodyGaudel"
COPY target/e-bank-0.0.1.jar e-bank-0.0.1.jar
ENTRYPOINT ["java","-jar","e-bank-0.0.1.jar"]
