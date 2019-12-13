# Spring学习

## 1、Spring依赖注入

### 1.1、 p命名空间和c命名空间注入

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:c="http://www.springframework.org/schema/c"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd">


    <bean id="user" class="com.how2java.demo02.User" p:name="龙神" p:age="18" />

    <bean id="useraa2" class="com.how2java.demo02.User" c:name="白手" c:age="14" />

</beans>
```



Test

```java
    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("userBean.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
        User user3 = context.getBean("user", User.class);
        System.out.println(user3);
        System.out.println(user == user3);
        User user2 = context.getBean("useraa2", User.class);
        System.out.println(user2);
        System.out.println(user == user2);
    }
```

### 1.2、 bean的作用域

<img src="C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20191213210903463.png" alt="image-20191213210903463"  />

| Scope                                                        | Description                                                  |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [singleton](https://docs.spring.io/spring/docs/5.2.2.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-singleton) | (Default) Scopes a single bean definition to a single object instance for each Spring IoC container. |
| [prototype](https://docs.spring.io/spring/docs/5.2.2.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-prototype) | Scopes a single bean definition to any number of object instances. |
| [request](https://docs.spring.io/spring/docs/5.2.2.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-request) | Scopes a single bean definition to the lifecycle of a single HTTP request. That is, each HTTP request has its own instance of a bean created off the back of a single bean definition. Only valid in the context of a web-aware Spring `ApplicationContext`. |
| [session](https://docs.spring.io/spring/docs/5.2.2.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-session) | Scopes a single bean definition to the lifecycle of an HTTP `Session`. Only valid in the context of a web-aware Spring `ApplicationContext`. |
| [application](https://docs.spring.io/spring/docs/5.2.2.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-application) | Scopes a single bean definition to the lifecycle of a `ServletContext`. Only valid in the context of a web-aware Spring `ApplicationContext`. |
| [websocket](https://docs.spring.io/spring/docs/5.2.2.RELEASE/spring-framework-reference/web.html#websocket-stomp-websocket-scope) | Scopes a single bean definition to the lifecycle of a `WebSocket`. Only valid in the context of a web-aware Spring `ApplicationContext`. |

1. singleton 单例模式(Spring默认机制)

```java
<bean id="user" class="com.how2java.demo02.User" p:name="龙神" p:age="18" />
    
<!--<bean id="user" class="com.how2java.demo02.User" p:name="白手" p:age="18" scope="singleton" />-->
```

2. protorype 原型模式：每次从容器中get的时候，都会产生一个新对象(深拷贝)

```java
<bean id="user" class="com.how2java.demo02.User" p:name="雄霸" p:age="18" scope="prototype"/>
```

3. 其余的request、session、application等只能在web开发中使用到！

## 2、Bean的自动装配

- 自动装配是Spring满足bean依赖的一种方式！
- Spring会在上下文中自动寻找，并自动给bean装配属性！

在Spring中有有一种装配的方式

1. 在xml中显示的配置
2. 在java中显示的配置
3. **隐式的自动装配bean 【重要】**

### 2.1、测试

环境搭建：一个人有2个宠物

### 2.2、byName自动装配

``` java
<!--byName会在容器上下文中查找和自己对象set方法后面的值对应的bean id-->
    <bean id="person" class="com.how2java.demo03.Person" autowire="byName" p:name="宋江" />
```

### 2.3、byType自动装配

``` java
<!--byType会在容器上下文中查找和自己对象属性类型对应的bean-->
    <bean id="person" class="com.how2java.demo03.Person" autowire="byType" p:name="宋江" />
```

小结：

- byName的时候，需要保证所有bean的id唯一，且这个bean需要和自动注入的属性的set方法的值一致！
- byType的时候， 需要保证所有的bean的class唯一，且这个bean需要和自动注入的属性的类型一致！
