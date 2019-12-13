package com.how2java.demo01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Animal cat = (Animal) ApplicationContext.getBean("cat", Cat.class);
        System.out.println(cat);
        cat.eat();

        Animal dog = (Animal) ApplicationContext.getBean("dog", Dog.class);
        System.out.println(dog);
        dog.eat();
    }
}
