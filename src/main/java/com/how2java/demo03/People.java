package com.how2java.demo03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public class People {
        //    如果显示定义了Autowired的required属性为false，说明这个对象可以为null，否则不允许为Null
//    @Autowired(required = false)
//    @Qualifier(value = "cat22")
        @Resource(name = "cat22")
        private Animal cat;
        //    @Autowired
//    @Qualifier(value = "dog22")
        @Resource(name = "dog22")
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

