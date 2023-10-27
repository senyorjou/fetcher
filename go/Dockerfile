# Build the application from source
FROM golang:1.19 AS build-stage

WORKDIR /app

COPY . .

RUN CGO_ENABLED=0 GOOS=linux go build -o app


# Deploy the application binary into a lean image
FROM scratch AS runner

WORKDIR /app

COPY --from=build-stage /app/app .
COPY capitals.json .


ENTRYPOINT ["/app/app"]