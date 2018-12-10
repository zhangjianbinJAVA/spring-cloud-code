package cn.springcloud.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 协调参与者，进行事务的 confirm 与 cancel
 */
@SpringBootApplication
public class TccCoordinatorAtomikosApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccCoordinatorAtomikosApplication.class, args);
    }
}
