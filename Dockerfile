FROM openjdk:14-slim

MAINTAINER albert.kulinski@hotmail.com

ARG JAR_FILE
ARG JAVA_PORT

COPY target/${JAR_FILE} subscriptiontrackerapi.jar

CMD ["java","-jar", "subscriptiontrackerapi.jar"]

VOLUME /var/lib/apiproxy

EXPOSE ${JAVA_PORT}
