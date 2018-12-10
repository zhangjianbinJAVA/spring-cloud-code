package cn.springcloud.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Ch2226BizServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch2226BizServiceApplication.class, args);
    }

    @RestController
    class webController {
        @GetMapping("/biz")
        public String bizTest() {
            return "zhangjianbin";
        }
    }
}
