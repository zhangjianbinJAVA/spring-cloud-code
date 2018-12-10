
https://github.com/spotify/dockerfile-maven

### jvm 参数
https://www.aliyun.com/jiaocheng/828954.html

### jvm 参数优化
https://www.howardliu.cn/java/jvm-tuning-basic/

https://www.ctolib.com/topics-113233.html

```
mvn clean package -Ddockerfile.skip
mvn dockerfile:build 
mvn dockerfile:push -Ddockerfile.username=caibosi@139.com -Ddockerfile.password=


docker run -p 8080:8080 --rm \
-e JAVA_OPTS='-server -Xmx1g -Xms1g -XX:MetaspaceSize=64m -verbose:gc -verbose:sizes -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -XX:+UnlockDiagnosticVMOptions -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/ -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -Xloggc:/opt/gc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=20M -Djava.io.tmpdir=/tmp' \
-e PROFILE='default' \
registry.cn-hangzhou.aliyuncs.com/springcloud-cn/ch22-1-3-jdk8-docker:0.0.1-SNAPSHOT


### jvm参数介绍           
docker run -p 7980:8080 --rm \
-e JAVA_OPTS='-server -Xmx1g -Xms1g -XX:MetaspaceSize=64m \ 
   -verbose:gc -verbose:sizes -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -XX:+UnlockDiagnosticVMOptions \ 
   -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/ -XX:+PrintGCDetails -XX:+PrintGCTimeStamps \ 
   -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -Xloggc:/opt/gc.log -XX:+UseGCLogFileRotation \ 
   -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=20M -Djava.io.tmpdir=/tmp' \
-e PROFILE='default' \
registry.cn-hangzhou.aliyuncs.com/springcloud-cn/ch22-1-3-jdk8-docker:0.0.1-SNAPSHOT


--rm 参数： 由于其只是在开发调试过程中短期运行，其用户数据并无保留的必要，
           因而可以在容器启动时设置--rm选项，这样在容器退出时就能够自动清理容器内部的文件系统
           
-server:一定要作为第一个参数
-Xms：最小堆的大小， 也就是当你的虚拟机启动后， 就会分配这么大的堆内存给你 
-Xmx：JVM最大的堆大小        

当最小堆占满后，会尝试进行GC，将-Xms和-Xmx的值配置为一样，可以避免每次垃圾回收完成后对JVM堆大小进行重新的调整
           
-XX:MaxPermSize:设置持久代大小

-verbose  在输出设备上显示虚拟机运行信息
-verbose:gc 现实垃圾收集信息

–XX:+UseG1GC 使用G1垃圾回收器

-XX:MaxGCPauseMillis 设置每次年轻代垃圾回收的最长时间，如果无法满足此时间，JVM会自动调整年轻代大小，以满足此值

--XX:+HeapDumpOnOutOfMemoryError 可以让JVM在出现内存溢出时候Dump出当前的内存转储快照

-XX:HeapDumpPath=${目录}参数表示生成DUMP文件的路径，如不设置，默认存储在jvm 运行环境目录

-XX:+PrintGCDetails 输出GC的详细日志

-XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）

-XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）

-XX:+PrintTenuringDistribution 指定JVM 在每次新生代GC时，输出幸存区中对象的年龄分布

-Xloggc: gc日志文件的输出路径

-XX:UseGCLogFileRotation 打开GC日志滚动记录功能，要求必须设置 -Xloggc参数
                         注，GC日志最好不滚动输出，因为之前的关键日志可能会被冲掉，日志写入同一个文件更方便分析

-XX:NumberOfGCLogFiles 设置滚动日志文件的个数，必须大于1 
                        日志文件命名策略是，<filename>.0, <filename>.1, ..., <filename>.n-1，其中n是该参数的值

-XX:GCLogFileSize  设置滚动日志文件的大小，必须大于8k ,当前写日志文件大小超过该参数值时，日志将写入下一个文件      

java.io.tmpdir  指定JVM的临时目录地址             
                        
    
```

