FROM frolvlad/alpine-oraclejdk8:slim
    MAINTAINER josejavier.blecua@kairosds.com

VOLUME /tmp
ADD bieber-tweets-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]