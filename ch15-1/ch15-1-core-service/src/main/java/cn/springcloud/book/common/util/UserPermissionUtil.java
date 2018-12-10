package cn.springcloud.book.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import cn.springcloud.book.common.vo.User;

public class UserPermissionUtil {

    /**
     * 模拟权限校验, 可以根据自己项目需要定制不同的策略,如查询数据库获取具体的菜单url或者角色等等.
     *
     * @param user
     */
    public static boolean verify(User user, HttpServletRequest request) {
        //根据请头 获取 服务名 ,判断用户是否有该服务的的权限
        String url = request.getHeader("x-user-serviceName");

        if (StringUtils.isEmpty(user)) {
            return false;
        } else {
            List<String> str = user.getAllowPermissionService();
            for (String permissionService : str) {
                if (url.equalsIgnoreCase(permissionService)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 模拟权限赋值, 可以根据自己项目需要定制不同的策略,如查询数据库获取具体的菜单url或者角色等等.
     *
     * @param user
     */
    public static void permission(User user) {
        if (user.getUserName().equals("admin")) {
            List allowPermissionService = new ArrayList();
            allowPermissionService.add("client-service");
            allowPermissionService.add("provider-service");

            //设置 用户的权限信息
            user.setAllowPermissionService(allowPermissionService);
        } else if (user.getUserName().equals("spring")) {
            List allowPermissionService = new ArrayList();
            allowPermissionService.add("client-service");
            user.setAllowPermissionService(allowPermissionService);
        } else {
            List allowPermissionService = new ArrayList();
            user.setAllowPermissionService(allowPermissionService);
        }
    }

}
