FROM maven:3.8.5-openjdk-17 as BUILDER
ARG VERSION=0.0.1-SNAPSHOT
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/

RUN mvn clean package
COPY target/TMSFrontend-${VERSION}.jar target/application.jar

FROM openjdk:17
WORKDIR /app/

COPY --from=BUILDER /build/target/application.jar /app/
EXPOSE 8081
CMD java -jar /app/application.jar --spring.profiles.active=docker