### STAGE 1: Build ###
FROM maven:3.8.4-openjdk-17-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package -DskipTests

### STAGE 2: Run ###
FROM openjdk:14-alpine
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Xmx128m","-jar","app.jar"]

