#FROM eclipse-temurin:17.0.4_8-jre-alpine
FROM openjdk:17-oracle

#RUN apk update && apk upgrade
ENV DIR_ROOT /uj/uploader

WORKDIR ${DIR_ROOT}
EXPOSE 8080
RUN pwd
COPY build/libs ${DIR_ROOT}/

ENTRYPOINT ["java", "-jar", "Uploader-0.0.1-SNAPSHOT.jar"]