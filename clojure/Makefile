.PHONY: build build-amd run run-amd

IMAGE_NAME=fetcher-clojure

build:
	docker buildx build -t $(IMAGE_NAME)-arm .

run:
	docker run --rm $(IMAGE_NAME)-arm

build-amd:
	docker buildx build -t $(IMAGE_NAME)-amd -f amd64.Dockerfile .

run-amd:
	docker run --rm $(IMAGE_NAME)-amd
