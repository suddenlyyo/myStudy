package com.zx.dynamicproxy;

import com.zx.dynamicproxy.service.Action;
import com.zx.dynamicproxy.serviceimpl.ActionImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @program: myStudy
 * @description:
 * @author: zhou  xun
 * @create: 2023-08-17 12:03
 */
public class ProxyTest {
    public static void main(String[] args) {
        //要代理的真实对象
        Action action = new ActionImpl();
        //代理对象的调用处理程序，我们将要代理的真实对象传入代理对象的调用处理的构造函数中，最终代理对象的调用处理程序会调用真实对象的方法
        InvocationHandler handler = new ActionHandle(action);
        //代理类的类加载器
        ClassLoader classLoader = handler.getClass().getClassLoader();
        //要实现的代理类的接口列表
        Class<?>[] interfaces = action.getClass().getInterfaces();
        Action proxy = (Action) Proxy.newProxyInstance(classLoader, interfaces, handler);
        System.out.println(proxy.eat());
    }

}
