package cn.springcloud.book.hystrix.config;

import java.util.concurrent.Callable;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 在执行请求前执行
 *
 * @param <S>
 */
public class HystrixThreadCallable<S> implements Callable<S> {
    private final RequestAttributes requestAttributes;
    private final Callable<S> delegate;
    private String params;

    public HystrixThreadCallable(Callable<S> callable, RequestAttributes requestAttributes, String params) {
        this.delegate = callable;
        this.requestAttributes = requestAttributes;
        this.params = params;
    }

    /**
     * 希望 将 RequestContextHolder 和 自定义的 HystrixThreadLocal 在 hystrix 线程中继续传递
     *
     * @return
     * @throws Exception
     */
    @Override
    public S call() throws Exception {
        try {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            HystrixThreadLocal.threadLocal.set(params);
            return delegate.call();
        } finally {
            RequestContextHolder.resetRequestAttributes();
            HystrixThreadLocal.threadLocal.remove();
        }
    }

}
