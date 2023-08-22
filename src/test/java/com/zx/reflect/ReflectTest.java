package com.zx.reflect;

import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
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
    public void getClazz() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IntrospectionException, InstantiationException {


//        Class<?> clazz2 = User.class;
//        Class<?> clazz3 = new User().getClass();
        Class<?> clazz = Class.forName("com.zx.model.User");
        // 创建实例对象
        Object obj = clazz.newInstance();
        //打印的是User类对应的字节码对象
        System.out.println("打印的目标类对应的字节码对象" + clazz);
        //获取User类对应的字节码对象clazz1的名字
        System.out.println("打印的目标类对应的字节码对象的名称" + clazz.getName());
        //通过User类对应的字节码对象，获取User类的类名
        System.out.println("打印的目标类对应的字节码对象的类名" + clazz.getSimpleName());
        //通过User类对应的字节码对象，获取User类对应的包对象
        System.out.println("打印的目标类对应的字节码对象的包" + clazz.getPackage());
        //通过User类对应的字节码对象，先获取User类对应的包对象，再获取这个包对象的名字
        System.out.println("打印的目标类对应的字节码对象的包名" + clazz.getPackage().getName());

        Field[] declaredFields = clazz.getDeclaredFields();
        //region 获取目标类对应的字段get、set方法
        for (Field field : declaredFields) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), clazz);
            //得到set方法
            Method setMethod = propertyDescriptor.getWriteMethod();
            // 获得get方法
            Method getMethod = propertyDescriptor.getReadMethod();
        }
        //endregion
        //获取方法
        Method method = clazz.getDeclaredMethod("toString");
        //调用方法
        method.invoke(obj);

    }


}
