FROM openjdk:17

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} todo.jar

ENTRYPOINT ["java","-jar","/todo.jar"]