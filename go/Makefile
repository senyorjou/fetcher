.PHONY: build run

IMAGE_NAME=fetcher-go

build:
	docker buildx build -t $(IMAGE_NAME) .

run:
	docker run --rm $(IMAGE_NAME)
