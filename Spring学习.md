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

### 2.4、使用注解实现自动装配

jdk1.5支持的注解，Spring2.5就支持注解了！



要使用注解须知：

1. 导入约束

2. **配置注解的支持：<context:annotation-config />**

   ```java
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd">
   
       <context:annotation-config/>
   
   </beans>
   ```

   

**@Autowired**

直接在属性上使用即可！也可以在set方式上使用！

使用@Autowired我们可以不用编写Set方法了，前提是你这个自动装配的属性在IOC(Spring) 容器中存在，且符合名称byName!

科普：

```xml
@Nullable	字段标记了这个注解，说明这个字段可以为null;
```

```java
public @interface Autowired {
    boolean required() default true;
}
```

```java
    @Autowired(required = false)
    private Animal cat;
    @Autowired
    private Animal dog;
    private String name;
```

如果@Autowired自动装配的环境比较复杂，自动装配无法通过一个注解【@Autowired】完成的时候，我们可以使用@Qualifier(value = "xxx")去配置@Autowired的使用，指定一个唯一的bean对象注入！

```java
public class People {
    @Autowired(required = false)
    @Qualifier(value = "cat22")
    private Animal cat;
    @Autowired
    @Qualifier(value = "dog22")
    private Animal dog;
    private String name;
}
```

**@Resource注解**

```java
public class People {
    @Resource(name = "cat22")
    private Animal cat;
    @Resource
    private Animal dog;
    private String name;
}
```



小结：@Resource注解和@Autowired注解的区别：

- 相同点：都是用来自动装配的，都可以放在属性字段上
- 不同点：
  - @Autowired 默认通过byType方式实现，失败再通过byName方式实现
  - @Resource 默认通过byName方式实现，找不到再通过byType方式实现，2个都匹配不到，就报错！

## 3、使用注解开发

在Spring4之后，要使用注解开发，必须要保证aop的包导入了

使用注解需要导入context约束，增加注解的支持！

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">

</beans>
```



1. bean

2. 属性如何注入

   ```java
   // 等价于 <bean id="user" class="com.kuang.dao.User" />
   // @Component 组件
   @Component
   public class User {
       // 相当于 <property name="name" value="吕布" />
       @Value("吕布")
       private String name;
   
       public String getName() {
           return name;
       }
   }
   ```

3. 衍生的注解

   @Component 有几个衍生注解，我们在web开发中，会按照MVC三层架构分层！

   - dao	【@Repository】

   - service 【@Service】

   - controller 【@Controller】

     这四个注解功能是一样的，都是代表将某个类注册到Spring容器中，装配Bean

4. 自动装配配置

   ```java
   - @Autowired：自动装配通过类型，名字
       如果Autowired不能唯一自动装配上属性，则需要通过@Qualifier(value = "xxx")
   - @Nullable ：字段标记了这个注解，说明这个字段可以为null;
   - @Resource ：自动装配通过名字，类型
   ```

5. 作用域

   ```java
   @Component
   @Scope("prototype")		//设置作用域为原型
   public class User {
       // 相当于 <property name="name" value="吕布" />
       @Value("吕布")
       private String name;
   
       public String getName() {
           return name;
       }
   }
   
   ```

   

6. 小结

   xml 与 注解：

   - xml 更加万能，适用于任何场合！维护简单方便
   - 注解 不是自己的类使用不了，维护相对复杂！

   xml 与 注解最佳实践：

   - xml 用来管理bean;

   - 注解只负责完成属性的注入；

   - 我们在使用的过程中，只需要注意一个问题：必须要注解生效，就需要开启注解的支持

     ```java
     <!--    指定要扫描的包，这个包下的注解就会生效-->
         <context:component-scan base-package="com.kuang" />
     <!--    配置注解的驱动（如果有了上面的那条，就不用写此条）-->
         <context:annotation-config />
     ```

## 4、使用Java的方式配置Spring

我们现在要完全不使用Spring的xml的配置了，全权交给Java来做！

JavaConfig 是Spring的一个字项目，在Spring4之后， 它成为了一个核心功能！



实体类：

```java
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
```

配置文件：

```java
// 这个也会被Spring容器托管, 注册到容器中, 因为它本来就是一个@Component
// @Configuration代表这是一个配置类, 就和之前看到的beans.xml是一样的
@Configuration
@ComponentScan("com.kuang.pojo")
@Import(MyConfig2.class)
public class MyConfig {

    // 注册一个bean, 就相当于我们之前写的一个bean标签
    // 这个方法的名字，相当于bean标签中的id属性
    // 这个方法的返回值, 就相当于bean标签中的class属性
    @Bean
    public User getUser(){
        return new User();
    }

}
```



测试类：

```java
public class MyTest {
    public static void main(String[] args) {
        // 如果完全使用了配置类的方式去做，我们就只能通过 AnnotationConfigApplicationContext 上下文来获取容器, 通过配置类的class对象加载
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        User user = context.getBean("getUser", User.class);
        System.out.println(user.getName());
    }
}
```

这种纯Java的配置方式，在Springboot中随处可见！