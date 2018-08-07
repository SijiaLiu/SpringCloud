#基于哪个镜像
FROM openjdk:8-jdk-alpine
#将本地文件挂载到当前容器
VOLUME /tmp
#复制文件到容器
ADD eureka-ha-0.0.1-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
#配置容器启动后需要执行的命令
ENTRYPOINT ["java","-Duser.timezone=Asia/Shanghai","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#需要暴露的端口
#EXPOSE 8761