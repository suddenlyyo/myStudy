package com.zx.reflect;


import com.zx.modle.User;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: myStudy
 * @description: 反射测试
 * @author: zhou  xun
 * @create: 2022-09-21 15:23
 */
public class ReflectTest {
    @Test
    public void getClazz() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //练习获取字节码对象的3种方式
        Class<?> clazz1 = Class.forName("com.zx.modle.User");
        Class<?> clazz2 = User.class;
        Class<?> clazz3 = new User().getClass();

        //打印的是User类对应的字节码对象
        System.out.println(clazz1);
        //获取User类对应的字节码对象clazz1的名字
        System.out.println(clazz1.getName());
        //通过User类对应的字节码对象，获取User类的类名
        System.out.println(clazz1.getSimpleName());
        //通过User类对应的字节码对象，获取User类对应的包对象
        System.out.println(clazz1.getPackage());
        //通过User类对应的字节码对象，先获取User类对应的包对象，再获取这个包对象的名字
        System.out.println(clazz1.getPackage().getName());
        Method method = clazz1.getMethod("getName");
        Method method1 = clazz1.getMethod("setName");
        method1.invoke(clazz1);
    }


}
