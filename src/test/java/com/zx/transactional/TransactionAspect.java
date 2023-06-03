package com.zx.transactional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @program: myStudy
 * @description: AspectJ 实现声明式事务示例
 * @author: zhou  xun
 * @create: 2023-06-03 20:29
 */
@Aspect
@Component
public class TransactionAspect {
    //AspectJ Java有以下通知类型：
//
//    Before：在目标方法执行之前执行通知。
//    After：在目标方法执行之后执行通知，无论目标方法是否抛出异常。
//    AfterReturning：在目标方法正常返回之后执行通知。
//    AfterThrowing：在目标方法抛出异常之后执行通知。
//    Around：包围目标方法的通知，可以在目标方法执行前后进行操作。
//
//这些通知类型可用于不同的场景，例如：
//
//    Before和After通知类型可用于日志记录、权限控制等方面。
//    AfterReturning和AfterThrowing通知类型可用于事务管理、异常处理等方面。
//    Around通知类型可用于性能监测、缓存管理等方面。
//
// 它们的作用是在目标方法执行前、执行后或者异常抛出时，插入一段额外的代码逻辑。
// 它们可以与其他 Java 框架结合使用，如 Spring AOP 和 Hibernate AOP 等。
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void transactions() {
    }

    //面向切面编程（AOP）是一种编程范式，它的主要思想是将程序中的横切关注点（如日志记录、性能统计、安全控制等）与核心业务逻辑分离出来，
    // 并使用特殊的语法和工具进行统一管理和维护，从而提高代码的可重用性、可维护性和可扩展性。
    //创建一个切面类，用于定义事务处理逻辑和通知类型
    //如果应用程序中的类没有被编译时织入，则事务可能会失效
    @Around("transactions()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            Object result = point.proceed();
            transactionManager.commit(status);
            return result;
        } catch (Throwable e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
    //在需要进行事务管理的方法上添加@Transactional注解
    //@Service
    //public class UserServiceImpl implements UserService {
    //
    //    @Autowired
    //    private UserRepository userRepository;
    //
    //    @Override
    //    @Transactional
    //    public User createUser(User user) {
    //        return userRepository.save(user);
    //    }
    //}
}

