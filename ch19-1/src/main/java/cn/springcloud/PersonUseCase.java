package cn.springcloud;

import cn.springcloud.proto.PersonModel;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 使用 proto buffers 做序列化和反序列化一个 java 对象的过程
 * <p>
 * 类似于 json  进行序列化和反序化
 * Created by forezp on 2018/6/9.
 */
public class PersonUseCase {

    public static void main(String[] args) {

        // 实例化 PersonModel
        PersonModel.Person forezp = PersonModel.Person.newBuilder()
                .setId(1)
                .setName("forezp")
                .setEmail("miles02@163.com").build();


        for (byte b : forezp.toByteArray()) {
            System.out.print(b);
        }

        System.out.println("\n" + "bytes长度" + forezp.toByteString().size());
        System.out.println("===== forezp Byte 结束 =====");

        System.out.println("===== forezp 反序列化生成对象开始 =====");
        PersonModel.Person forezpCopy = null;
        try {
            forezpCopy = PersonModel.Person.parseFrom(forezp.toByteArray());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        System.out.print(forezpCopy.toString());
        System.out.println("===== forezp 反序列化生成对象结束 =====");
    }
}
