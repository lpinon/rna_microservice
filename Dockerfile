FROM amazoncorretto:11-alpine-jdk
COPY target/rna_microservice-0.0.1.jar server.jar
ENTRYPOINT ["java","-jar","/server.jar"]