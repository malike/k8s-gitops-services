#!make

BACKEND_IMAGE_NAME := ghcr.io/malike/k8s-gitops-services/gitops-backend-service
FRONTEND_IMAGE_NAME := ghcr.io/malike/k8s-gitops-services/gitops-frontend-service
TAG := latest


.PHONY:
clean-backend: ## Clean services
	pushd backend && mvn clean

.PHONY:
build-backend: clean-backend ## Build java backend service
	pushd backend && mvn compile && mvn package

.PHONY:
docker-build-backend: ## Build backend docker image
	pushd backend && docker build -f Dockerfile -t ${BACKEND_IMAGE_NAME}:${TAG} .

.PHONY: docker-build-backend
docker-build-backend-push:  docker-build-backend ## Build and push backend docker image to registry
	docker push ${BACKEND_IMAGE_NAME}:${TAG}

.PHONY:
docker-build-frontend: ## Build frontend docker image
	pushd frontend && docker build -f Dockerfile -t ${FRONTEND_IMAGE_NAME}:${TAG} .

.PHONY:
docker-build-frontend-push: docker-build-frontend ## Build and push backend docker image to registry
	docker push ${FRONTEND_IMAGE_NAME}:${TAG}

.PHONY:
help: ## Display this help screen
	@grep -h -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
