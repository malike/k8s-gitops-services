FROM maven:3.9.5-sapmachine-21 AS build
RUN apt-get update
RUN apt install build-essential -y --no-install-recommends
COPY config /home/app/config
COPY src /home/app/src
COPY pom.xml /home/app
RUN cd /home/app && mvn clean && mvn package

FROM openjdk:22-ea-21-slim
VOLUME /tmp
COPY --from=build /home/app/target/*.jar gitops-observability-demo-0.0.1-SNAPSHOT.jar
RUN sh -c 'touch ./gitops-observability-demo-0.0.1-SNAPSHOT.jar'
ENV JAVA_OPTS="-Xms64m -Xmx64m"
EXPOSE 9090
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar gitops-observability-demo-0.0.1-SNAPSHOT.jar" ]