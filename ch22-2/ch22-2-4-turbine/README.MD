## local build & run
```
mvn clean package -Ddockerfile.skip

CONFIG_SERVER_HOST=localhost CONFIG_SERVER_PORT=8888 SERVER_PORT=8001 EUREKA_SERVER1_HOST=localhost EUREKA_SERVER1_PORT=8761 EUREKA_SERVER2_HOST=localhost EUREKA_SERVER2_PORT=8762 java -jar target/ch22-2-4-turbine-0.0.1-SNAPSHOT.jar
```

http://localhost:8001/hystrix


## docker
```
mvn clean package

docker run -p 8001:8080 --rm \
-e JAVA_OPTS='-server -Xmx1g' \
-e PROFILE='default' \
-e SERVER_PORT=8080 \
-e CONFIG_SERVER_HOST=192.168.179.117 \
-e CONFIG_SERVER_PORT=8888 \
-e EUREKA_SERVER1_HOST=192.168.179.117 \
-e EUREKA_SERVER1_PORT=8761 \
-e EUREKA_SERVER2_HOST=192.168.179.117 \
-e EUREKA_SERVER2_PORT=8762 \
--name turbine-server \
registry.cn-hangzhou.aliyuncs.com/springcloud-cn/ch22-2-4-turbine:0.0.1-SNAPSHOT
```

### test
```
http://192.168.179.117:8001/hystrix

http://192.168.179.117:8000/actuator/hystrix.stream
```