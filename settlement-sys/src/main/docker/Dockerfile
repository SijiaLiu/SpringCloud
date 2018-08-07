FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD settlement-sys-0.0.1-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Duser.timezone=Asia/Shanghai","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#EXPOSE 8763