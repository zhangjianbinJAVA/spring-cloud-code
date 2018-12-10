package cn.springcloud.book.hystrix.config;

public class HystrixThreadLocal {
    /**
     * 把当前请求的上下文数据放入本地线程变量
     */
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
}