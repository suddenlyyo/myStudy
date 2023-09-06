package com.zx.java8;

import com.github.jsonzou.jmockdata.JMockData;
import com.zx.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @program: myStudy
 * @description: 函数式接口测试
 * @author: zhou  xun
 * @create: 2023-09-06 18:24
 */
public class FunctionalInterfacesTest {
    @Test
    @DisplayName("Consumers Test")
    public void consumersTest() {
        // 创建一个Consumer，它接受一个User参数并打印用户名
        //表示接受单个输入参数但不返回任何结果的操作
        Consumer<User> greeter = (u) -> System.out.println("Hello, " + u.getName());
        //accept接口方法负责接收真实的参数
        greeter.accept(JMockData.mock(User.class));
    }

    @Test
    @DisplayName("Consumers andThen Test")
    public void consumersAndThenTest() {
        //Consumer接口的andThen方法允许你将一个Consumer与另一个Consumer链接在一起。
        // 第一个Consumer首先执行，然后执行第二个Consumer。这允许你在一个操作完成后执行另一个操作。

        // 创建一个Consumer，它接受一个字符串参数并打印该字符串
        Consumer<String> firstConsumer = str -> System.out.println("First Consumer: " + str);

        // 创建一个Consumer，它接受一个字符串参数并将该字符串转换为大写
        Consumer<String> secondConsumer = str -> System.out.println("Second Consumer: " + str.toUpperCase());

        // 将两个Consumer链接在一起，第一个Consumer首先执行，然后执行第二个Consumer
        Consumer<String> composedConsumer = firstConsumer.andThen(secondConsumer);

        // 使用组合的Consumer对字符串进行操作
        composedConsumer.accept("Hello World!");
    }

    @Test
    @DisplayName("Suppliers Test")
    public void suppliersTest() {
//        Supplier<User> userSupplier = User::new;
//        User user = userSupplier.get();// new User
//        System.out.println(user);// 返回的是应该空的user对象
        //延迟计算
        Supplier<LocalDateTime> currentTime = LocalDateTime::now;
        LocalDateTime now = currentTime.get(); // 获取当前时间
        //随机数生成
        Supplier<Double> randomDouble = Math::random;
        double rand = randomDouble.get(); // 产生一个随机数
    }

}
