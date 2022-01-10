FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/backend-0.1.jar
EXPOSE 8000
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-Xmx128m", "-jar", "application.jar"]