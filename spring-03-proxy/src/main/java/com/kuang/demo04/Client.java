package com.kuang.demo04;

import com.kuang.demo02.UserService;
import com.kuang.demo02.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class Client {
    public static void main(String[] args) {
        // 真实角色
        UserService userService = new UserServiceImpl();
        // 代理角色：不存在
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        // 注入要代理的对象
        pih.setTarget(userService);
        // 动态生成代理类
        UserService proxy = (UserService) pih.getProxy();

        proxy.add();
    }                 
}
