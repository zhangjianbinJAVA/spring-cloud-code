FROM wutang/skywalking-agent:latest
LABEL maintainer="jian.tan@daocloud.io"

ARG JAR_FILE
ENV PROFILE default

ENV SW_APPLICATION_CODE=java-eureka-application \
	SW_COLLECTOR_SERVERS=192.168.171.124:10800

ADD target/${JAR_FILE} /opt/app.jar
EXPOSE 8080


ENTRYPOINT java -javaagent:/apache-skywalking-apm-incubating/agent/skywalking-agent.jar -Dskywalking.collector.servers=${SW_COLLECTOR_SERVERS} \
-Dskywalking.agent.application_code=${SW_APPLICATION_CODE}  ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -Duser.timezone=Asia/Shanghai -Dfile.encoding=UTF-8 -Dspring.profiles.active=${PROFILE} -jar /opt/app.jar