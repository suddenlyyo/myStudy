package com.zx.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;


/**
 * @program: myStudy
 * @description: 系统bean帮助类
 * @author: zhou  xun
 * @create: 2023-07-04 14:19
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;


    public static Object getBean(String name) {
        return context.getBean(name);
    }

    public static <T> T getBean(Class<T> c) {
        return context.getBean(c);
    }

    public String getMessage(String key) {
        return context.getMessage(key, null, Locale.getDefault());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}