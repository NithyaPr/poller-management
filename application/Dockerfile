FROM openjdk:11
EXPOSE 8081
ARG JAR_FILE=target/poller-management-*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM mysql
MAINTAINER nithya.balusamy@gmail.com

ENV MYSQL_ROOT_PASSWORD password
ADD jsp_backup.sql /docker-entrypoint-initdb.d

EXPOSE 3306