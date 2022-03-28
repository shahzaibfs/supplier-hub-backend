FROM openjdk:11
LABEL maintainer ="shahzaib"
ADD target/supplier-hub.jar docker-supplier-hub.jar
ENTRYPOINT ["java","-jar","docker-supplier-hub.jar"]