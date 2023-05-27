package com.zx.multithreading;

/**
 * @program: myStudy
 * @description:
 * @author: zhou  xun
 * @create: 2023-05-21 20:08
 */
public class SynchronizedExample {
    private final Object lock = new Object(); // 创建一个对象用于锁定操作

    //同一时间只有一个线程能够执行其中一个方法
    public void foo() {
        synchronized (lock) {
            // 同步块内容
        }
    }

    public void bar() {
        synchronized (lock) {
            // 同步块内容
        }
    }
}
