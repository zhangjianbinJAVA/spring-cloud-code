package cn.springcloud.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
/**
 *
 * @EnableOAuth2Sso 启动oauth2.0的单点登录
 *
 * 这个注解会帮我们完成跳转到授权服务器
 */
@EnableOAuth2Sso
public class ZuulServerApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }

    /**
     * 声明需要鉴权的url信息
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                // login 和 client 路径放行
                .antMatchers("/login", "/client/**")
                .permitAll()

                //其它请求都的需要认证
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
    }
}
