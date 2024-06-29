FROM openjdk:23-slim-bullseye
WORKDIR /app
COPY ./target/Telegram_new-1.0-SNAPSHOT-jar-with-dependencies.jar .

CMD ["java", "-jar", "Telegram_new-1.0-SNAPSHOT-jar-with-dependencies.jar"]
