FROM maven:3.6.0-amazoncorretto-11 as maven

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn package

FROM amazoncorretto:11-alpine-jdk
ARG JAR_FILE=technical-test-0.0.1.jar
WORKDIR /opt/app
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]