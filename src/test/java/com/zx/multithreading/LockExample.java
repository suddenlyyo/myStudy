package com.zx.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @program: myStudy
 * @description:
 * @author: zhou  xun
 * @create: 2023-05-21 20:05
 */
public class LockExample {
    private final Lock lock = new ReentrantLock();

    public void foo() {
        lock.lock(); // 获取锁
        try {
            // 同步块内容
        } finally {
            lock.unlock(); // 释放锁
        }
    }

//    Java 中提供了多种类型的锁，下面是常见的几种：
//    synchronized 锁：Java 中最基本的锁机制之一，使用 synchronized 关键字实现同步，能够在单线程和多线程环境下保证数据的安全性。
//
//    ReentrantLock 锁：JDK 提供的可重入锁，与 synchronized 工作的方式类似，但具有更加灵活的控制和扩展性，例如可以设置公平锁和非公平锁，支持中断、超时等操作。
//
//    ReadWriteLock 锁：读写锁，可以分别对读和写操作进行加锁，避免多个读操作互相影响，提高了并发读取的效率。
//
//    StampedLock 锁：JDK 8 新增的锁机制，与 ReadWriteLock 类似，支持读写锁和乐观锁（optimistic lock）模式，并提供了更好的并发性能和扩展性。
//
//    SpinLock 锁：自旋锁，使用 spin 循环的方式等待锁的释放而不是让线程进入等待状态，减少线程切换和上下文切换的开销，适合锁的竞争比较激烈的场景。
//
//    Condition 条件变量：在多线程编程中，有些情况下需要线程之间进行通信和协作，例如生产者消费者模式，这时可以使用 Condition 条件变量进行控制。
//
//    以上是常用的几种锁机制，不同的锁机制适用于不同的场景，选择合适的锁机制可以提高代码的并发性能和可靠性。

    public void bar() {
        lock.lock(); // 获取锁
        try {
            // 同步块内容
        } finally {
            lock.unlock(); // 释放锁
        }
    }
}
