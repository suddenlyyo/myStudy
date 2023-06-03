package com.zx.java8;

import org.junit.jupiter.api.RepeatedTest;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @program: myStudy
 * @description: 重复注解测试
 * @author: zhou  xun
 * @create: 2023-06-03 12:26
 */
public class RepeatableTest {
    //region定义重复注解
    @Repeatable(Fruits.class)
    public @interface Fruit {
        String name() default "";

        String color() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Fruits {
        Fruit[] value();
    }

    //endregion
    //region 使用重复注解
    @Fruits({@Fruit(name = "apple", color = "red"),
            @Fruit(name = "banana", color = "yellow")})
    static class FruitDemo {
    }

    //endregion
    public static void main(String[] args) {
        //获取注解信息
        Fruits fruits = FruitDemo.class.getAnnotationsByType(Fruits.class)[0];
        for (Fruit fruit : fruits.value()) {
            System.out.println(fruit.name() + " is " + fruit.color());
        }
    }

}
