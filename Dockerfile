FROM --platform=linux/amd64 openjdk:21

EXPOSE 8080

WORKDIR /app

COPY ./target/ilp_submission_1-0.0.1-SNAPSHOT.jar app.jar

LABEL authors="oisinrice"

ENTRYPOINT ["java", "-jar", "app.jar"]