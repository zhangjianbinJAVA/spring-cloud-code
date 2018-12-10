/*
 * Copyright 2016 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.springcloud.grpcsimpleclient;

import cn.springcloud.grpc.HelloRequest;
import cn.springcloud.grpc.HelloResponse;
import cn.springcloud.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Created by rayt on 5/16/16.
 */
public class MyGrpcClient {

    public static void main(String[] args) throws InterruptedException {

        // 创建一个连接 gRPCServer 的 Channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8082)
                //表明用纯文本创建连接，默认的情况下，会使用 TLS 安全连接机制
                .usePlaintext()
                .build();

        // 根据 该 Channel 创建一个阻塞 的 stub
        HelloServiceGrpc.HelloServiceBlockingStub stub =
                HelloServiceGrpc.newBlockingStub(channel);

        // 该 stub 向 gRPCServer 发送一条 HelloRequest 消息，并阻塞线程直到连接到 gRPCServer
        // 发回的 HelloResponse 响应
        HelloResponse helloResponse = stub.hello(
                HelloRequest.newBuilder()
                        .setName("forezp")
                        .setAge(17)
                        .addHobbies("football").putTags("how?", "wonderful")
                        .build());

        // 打印响应
        System.out.println(helloResponse);

        // 关闭 Channel
        channel.shutdown();
    }
}
