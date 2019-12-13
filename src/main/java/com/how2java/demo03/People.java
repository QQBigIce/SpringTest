package com.how2java.demo03;

public class People {
    private Animal animal1;
    private Animal animal2;
    private String name;

    public Animal getAnimal1() {
        return animal1;
    }

    public void setAnimal1(Animal animal1) {
        this.animal1 = animal1;
    }

    public Animal getAnimal2() {
        return animal2;
    }

    public void setAnimal2(Animal animal2) {
        this.animal2 = animal2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "animal1=" + animal1 +
                ", animal2=" + animal2 +
                ", name=" + name +
                '}';
    }
}
