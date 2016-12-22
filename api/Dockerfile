FROM openjdk:8

COPY . app

WORKDIR app

RUN echo $DB_URL

ENTRYPOINT ["./gradlew", "flywayMigrate", "-i", "run"]
