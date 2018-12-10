#### apline + glibc + jdk8

```
docker pull registry.cn-hangzhou.aliyuncs.com/springcloud-cn/java:8u172-jre-alpine
```


### Java 8u131及以上版本开始支持了Docker的cpu和memory限制
- cpu limit

 即如果没有显式指定-XX:ParalllelGCThreads 或者 -XX:CICompilerCount,那么JVM使用docker的cpu限制。
 如果docker有指定cpu limit，jvm参数也有指定-XX:ParalllelGCThreads 或者 -XX:CICompilerCount，
 那么以指定的参数为准。

- memory limit

在java8u131+及java9，需要加上-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap
才能使得 Xmx 感知 docker的 memory limit。

### 查看参数默认值
````
java -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions -XX:+PrintFlagsFinal
````

### jdk10 
新引入 ActiveProcessorCount 可以用来强制指定cpu的个数，废除 UseCGroupMemoryLimitForHeap

### jdk11
废除 UseCGroupMemoryLimitForHeap ,新引入 UseContainerSupport 默认为true，
可以使用-Xlog:os+container=trace参数来查看详情。(即默认支持 Docker 的 cpu 和 内存限制，)

即使使用-XX:-UseContainerSupport=false 禁用了容器支持，
-XX:ActiveProcessorCount 如果有指定，该参数值仍然会被使用。

