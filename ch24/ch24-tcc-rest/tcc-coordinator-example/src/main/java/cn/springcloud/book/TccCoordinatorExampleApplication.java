package cn.springcloud.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 演示 rest tcc 进行分布式事务管理
 *
 *
 */
@SpringBootApplication
public class TccCoordinatorExampleApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TccCoordinatorExampleApplication.class, args);
        SpringApplication app = new SpringApplication(TccCoordinatorExampleApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }
}
