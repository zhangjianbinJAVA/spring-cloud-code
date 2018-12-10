package cn.springcloud.book.common.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

import cn.springcloud.book.common.util.UserPermissionUtil;
import cn.springcloud.book.common.vo.User;

public class UserContextInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(UserContextInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse respone, Object arg2) throws Exception {

        // 从请求头中获取用户信息
        User user = getUser(request);

        // 获取 用户 的 权限信息,并设置用户的权限信息
        UserPermissionUtil.permission(user);

        if (!UserPermissionUtil.verify(user, request)) {
            respone.setHeader("Content-Type", "application/json");
            String jsonstr = JSON.toJSONString("no permisson access service, please check");
            respone.getWriter().write(jsonstr);
            respone.getWriter().flush();
            respone.getWriter().close();
            throw new PermissionException("no permisson access service, please check");
        }

        //上下文中存入用户信息
        UserContextHolder.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse respone, Object arg2, ModelAndView arg3)
            throws Exception {
        // DOING NOTHING
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse respone, Object arg2, Exception arg3)
            throws Exception {

        // 清空用户信息
        UserContextHolder.shutdown();
    }

    private User getUser(HttpServletRequest request) {
        String userid = request.getHeader("x-user-id");
        String username = request.getHeader("x-user-name");
        User user = new User();
        user.setUserId(userid);
        user.setUserName(username);
        return user;
    }

    static class PermissionException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public PermissionException(String msg) {
            super(msg);
        }
    }
}
