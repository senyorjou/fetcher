FROM clojure:temurin-21-lein-2.10.0-alpine AS builder

WORKDIR /app

COPY . .

RUN lein uberjar

FROM clojure:temurin-21-alpine

WORKDIR /app

COPY --from=builder /app/target/uberjar/isjustok-0.1.0-SNAPSHOT-standalone.jar ./app.jar
COPY resources ./resources

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
