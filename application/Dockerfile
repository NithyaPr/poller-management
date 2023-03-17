FROM openjdk:11
EXPOSE 8081
ARG JAR_FILE=target/poller-management-1.0.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]