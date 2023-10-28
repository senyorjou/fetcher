.PHONY: build run

IMAGE_NAME=fetcher-js

build:
	docker buildx build -t $(IMAGE_NAME) .

run:
	docker run --rm $(IMAGE_NAME)
