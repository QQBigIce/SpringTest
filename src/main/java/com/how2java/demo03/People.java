package com.how2java.demo03;

import org.springframework.beans.factory.annotation.Autowired;

public class People {
    @Autowired
    private Animal cat;
    @Autowired
    private Animal dog;
    private String name;

    public Animal getCat() {
        return cat;
    }

    public void setCat(Animal cat) {
        this.cat = cat;
    }

    public Animal getDog() {
        return dog;
    }

    public void setDog(Animal dog) {
        this.dog = dog;
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
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}

