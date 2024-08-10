FROM openjdk:17-jdk-slim

VOLUME /tmp

COPY build/libs/sudoku-0.0.1.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]