package com.kuang.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
// 这里这个注解和意思, 就是说明这个类被Spring接管了, 注册到了容器中
@Component
public class User {
    @Value("奉先") //属性注入值
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
