package com.how2java.demo02;

import com.how2java.demo03.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext ApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Animal cat = (Animal) ApplicationContext.getBean("cat", Cat.class);
        System.out.println(cat);
        cat.eat();

        Animal dog = (Animal) ApplicationContext.getBean("dog", Dog.class);
        System.out.println(dog);
        dog.eat();
    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
        User user2 = context.getBean("user", User.class);
        System.out.println(user2);
        System.out.println(user == user2);
    }

    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("demo03.xml");
        People people = context.getBean("people", People.class);
        people.getDog().eat();
        people.getCat().eat();
        System.out.println(people);
        Person person = context.getBean("person", Person.class);
        person.getCat().eat();
        person.getDog().eat();
        System.out.println(person);
        People people1 = context.getBean("people1", People.class);
        System.out.println(people1);
    }

}
