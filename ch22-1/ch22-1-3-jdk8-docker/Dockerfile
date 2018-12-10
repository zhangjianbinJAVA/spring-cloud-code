FROM registry.cn-hangzhou.aliyuncs.com/springcloud-cn/java:8u172-jre-alpine

#ARG 指令 定义了一个变量，能让用户可以在构建期间使用docker build命令和其参数–build-arg =对这个变量赋值
# JAR_FILE 在 spring-boot-maven-plugin 的配置中指定
ARG JAR_FILE

# 暴露环境变量
ENV PROFILE default
ADD target/${JAR_FILE} /opt/app.jar
EXPOSE 8080
# JAVA_OPTS 允许不同应用去指定 jvm 参数
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -Duser.timezone=Asia/Shanghai -Dfile.encoding=UTF-8 -Dspring.profiles.active=${PROFILE} -jar /opt/app.jar