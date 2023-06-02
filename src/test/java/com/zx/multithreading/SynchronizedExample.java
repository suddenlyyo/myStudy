package com.zx.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
//    使用synchronized是一种更简单的方式来控制并发访问共享资源，可以在代码块或方法中直接使用。它会自动获取和释放锁，并且具有天然的可重入性。
//
//    相比之下，ReentrantLock提供了更多的灵活性和可定制性，例如支持公平锁和非公平锁、支持可中断锁和超时锁等特性。但是，使用ReentrantLock需要手动获取和释放锁，并且需要显式地处理异常情况。另外，如果不小心忘记释放锁，可能会导致死锁或其他问题。
//
//    因此，一般来说，在不需要ReentrantLock提供的高级特性时，使用synchronized更为方便和安全。只有在需要更细粒度的控制，或者遇到了某些特殊情况（例如需要支持可中断锁），才考虑使用ReentrantLock。


//    Java中的读写锁示例：
//
//    ReadWriteLock rwLock = new ReentrantReadWriteLock();
//    rwLock.readLock().lock(); // 读取之前要获取读锁
//    try {
//        // 读取数据操作
//    } finally {
//        rwLock.readLock().unlock(); // 读取完成后释放读锁
//    }
//
//   rwLock.writeLock().lock(); // 写入之前要获取写锁
//   try {
//        // 写入数据操作
//    } finally {
//        rwLock.writeLock().unlock(); // 写入完成后释放写锁
//    }
//
//    Java中的悲观锁示例：
//
//    synchronized (object) { // 对共享资源进行加锁
//        // 操作共享资源
//    }
//
//    Java中的乐观锁示例：
//
//    AtomicInteger atomicInt = new AtomicInteger(0);
//   先检查当前值是否为期望值，如果是则更新为新值，否则不做任何操作
//    atomicInt.compareAndSet(expectedValue, newValue);

}
