package cn.springcloud.book.gateway.filter;

import java.net.URI;
import java.util.Map;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter {


    /**
     * 所有请求都请经过 改  filter
     * <p>
     * 对  jwt  token 进行解析校验
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取由路信息
        Route gatewayUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        URI uri = gatewayUrl.getUri();

        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();
        HttpHeaders header = request.getHeaders();

        // 从请求头中获取 token
        String token = header.getFirst(JwtUtil.HEADER_AUTH);
        // 校验 token，获取　用户信息
        Map<String, String> userMap = JwtUtil.validateToken(token);

        ServerHttpRequest.Builder mutate = request.mutate();
        if (userMap.get("user").equals("admin") || userMap.get("user").equals("spring") || userMap.get("user").equals("cloud")) {

            // 用户信息 存放于 请求头中，传递给后端服务
            mutate.header("x-user-id", userMap.get("id"));
            mutate.header("x-user-name", userMap.get("user"));
            // 当前访问的服务名，后台服务可以根据用户信息判断是否有权访问
            mutate.header("x-user-serviceName", uri.getHost());
        } else {
            throw new PermissionException("user not exist, please check");
        }

        ServerHttpRequest buildReuqest = mutate.build();
        return chain.filter(exchange.mutate().request(buildReuqest).build());
    }
}
