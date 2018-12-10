```
mvn clean package

docker run -p 8888:8080 --rm \
-e JAVA_OPTS='-server -Xmx1g' \
-e PROFILE='jdbc' \
-e SERVER_PORT=8080 \
registry.cn-hangzhou.aliyuncs.com/springcloud-cn/ch22-2-1-config-server:0.0.1-SNAPSHOT
```

### 117
```
docker run \
--env JAVA_OPTS='-server -Xmx1g' \
--env PROFILE=jdbc \
--env SERVER_PORT=8080 \
--name config-server \
-p 8888:8080 \
--rm \
registry.cn-hangzhou.aliyuncs.com/springcloud-cn/ch22-2-1-config-server:0.0.1-SNAPSHOT 
```

### 测试
```
http://192.168.179.117:8888/eureka-server/default/master
```