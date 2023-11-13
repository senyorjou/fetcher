.PHONY: build run

IMAGE_NAME=fetcher-scala:1.0.0

build:
	sbt Docker/publishLocal

run:
	docker run --rm $(IMAGE_NAME)
