.PHONY: build run

IMAGE_NAME=fetcher-scala:1.0.0

build:
        cd target/docker/stage
	docker build -t $IMAGE_NAME .

run:
	docker run --rm $(IMAGE_NAME)
