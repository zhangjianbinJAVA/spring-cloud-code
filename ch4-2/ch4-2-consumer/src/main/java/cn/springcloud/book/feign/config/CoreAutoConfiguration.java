package cn.springcloud.book.feign.config;

import feign.Logger;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CoreAutoConfiguration {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FeignRequestInterceptor charlesRequestInterceptor() {
        return new FeignRequestInterceptor();
    }

    @Bean
    public Logger.Level feignLog() {
        return Logger.Level.FULL;
    }

    //@Bean
    //public okhttp3.OkHttpClient okhttp(){
    //    return new OkHttpClient()
    //            .newBuilder()
    //            .connectionPool(new ConnectionPool())
    //            .build();
    //}
}
