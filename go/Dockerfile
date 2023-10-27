# Build the application from source
FROM golang:1.19 AS build-stage

WORKDIR /app

COPY . .

RUN CGO_ENABLED=0 GOOS=linux go build -o app


FROM gcr.io/distroless/static-debian12 AS runner

WORKDIR /app

COPY --from=build-stage /app/app .
COPY capitals.json .


ENTRYPOINT ["/app/app"]
