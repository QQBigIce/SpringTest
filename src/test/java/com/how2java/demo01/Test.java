package com.how2java.demo01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Cat cat = (Cat) ApplicationContext.getBean("cat", Cat.class);
        System.out.println(cat.getName());
        System.out.println(cat.getAge());
        cat.eat();
    }
}
