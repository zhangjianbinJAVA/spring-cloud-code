### JDK对docker容器的支持

https://cloud.tencent.com/developer/article/1340356


## set jdk 10 to build
```
jenv local 10
jenv enable-plugin maven
```

## build
```
mvn clean package
docker build --build-arg JAR_FILE=ch22-1-4-jdk10-docker-0.0.1-SNAPSHOT.jar \
-t registry.cn-hangzhou.aliyuncs.com/springcloud-cn/ch22-1-4-jdk10-docker:0.0.1-SNAPSHOT .
```

## run
```
docker run -p 8080:8080 --rm \
-e JAVA_OPTS='-server -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:ActiveProcessorCount=1 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/ -Xlog:age*,gc*=info:file=gc-%p-%t.log:time,tid,tags:filecount=5,filesize=10m -Djava.io.tmpdir=/tmp' \
-e PROFILE='default' \
registry.cn-hangzhou.aliyuncs.com/springcloud-cn/ch22-1-4-jdk10-docker:0.0.1-SNAPSHOT



## jvm 参数介绍
docker run -p 8080:8080 --rm \
-e JAVA_OPTS='-server -XX:+UseG1GC -XX:MaxGCPauseMillis=50 \ 
-XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions \ 
-XX:+UseCGroupMemoryLimitForHeap -XX:ActiveProcessorCount=1 \ 
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/  \ 
-Xlog:age*,gc*=info:file=gc-%p-%t.log:time,tid,tags:filecount=5,filesize=10m \ 
-Djava.io.tmpdir=/tmp' \
-e PROFILE='default' \
registry.cn-hangzhou.aliyuncs.com/springcloud-cn/ch22-1-4-jdk10-docker:0.0.1-SNAPSHOT


-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap
才能使得Xmx感知docker的 memory limit

-XX:ActiveProcessorCount 可以用来指定cpu的个数

java9把gc log的配置统一到了Xlog中

info 日志消息关联的级别为 info
file 文件我名
time ISO-8601格式的当前时间和日期
tid 线程标识符
tags 日志消息关联的标记集


```