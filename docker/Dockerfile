FROM openjdk:17
LABEL authors="alex"

WORKDIR /project

ARG PROJECT_JAR=*.jar

COPY ${PROJECT_JAR} adapter-service.jar

ENTRYPOINT ["java", "-jar", "adapter-service.jar"]