package cn.springcloud.grpcsimpleserver.servcie;

import cn.springcloud.grpc.HelloResponse;
import cn.springcloud.grpc.HelloServiceGrpc;


/**
 * Email miles02@163.com
 *
 * @author fangzhipeng
 * create 2018-06-11
 **/
public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {


    /**
     * 该方法中打印了从客户端发送过来的 HelloRequest 对象，
     * <p>
     * 并根据 HelloRequest 对象生成一个 HelloResponse 对象
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void hello(cn.springcloud.grpc.HelloRequest request,
                      io.grpc.stub.StreamObserver<cn.springcloud.grpc.HelloResponse> responseObserver) {
        System.out.println(request);

        String greeting = "Hi " + request.getName() + " you are " + request.getAge() + " years old" +
                " your hoby is " + (request.getHobbiesList()) + " your tags " + request.getTagsMap();

        HelloResponse response = HelloResponse.newBuilder().setGreeting(greeting).build();

        // 向流中写入 HelloResponse 对象
        responseObserver.onNext(response);

        // 向流中写入结束
        responseObserver.onCompleted();
    }
}
