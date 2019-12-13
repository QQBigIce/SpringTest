package com.how2java.demo03;

public class Cat extends Animal {
    public Cat() {
    }

    public Cat(String name, int age) {
        super(name, age);
    }

    public void eat() {
        System.out.println("猫吃鱼");
    }

}
