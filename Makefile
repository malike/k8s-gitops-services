#!make

BACKEND_IMAGE_NAME := k8s-gitops-backend
FRONTEND_IMAGE_NAME := k8s-gitops-backend
TAG := latest


.PHONY:
clean-backend:
	cd backend
	mvn clean

.PHONY:
build-backend: clean-backend
	mvn compile
	mvn package

.PHONY:
docker-build-backend: build-backend
	docker build -f Dockerfile -t ${BACKEND_IMAGE_NAME}:${TAG} .

.PHONY:
docker-build-frontend:
	cd frontend
	docker build -f Dockerfile -t ${FRONTEND_IMAGE_NAME}:${TAG} .
