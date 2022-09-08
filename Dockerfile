#pichincha test

FROM openjdk:18-jdk-alpine3.14

LABEL maintainer="javaedi.et@gmail.com"

RUN apk add --update netcat-openbsd && rm -rf /var/cache/apk/*
RUN apk add --no-cache cifs-utils

COPY target/*.jar /app.jar

RUN echo "/bin/sh -c " > entrypoint.sh
RUN echo "java \$API_JAVA_OPTION -jar /app.jar" >> entrypoint.sh
RUN chmod 755 entrypoint.sh
RUN cat entrypoint.sh

EXPOSE 8080

CMD /bin/sh ./entrypoint.sh