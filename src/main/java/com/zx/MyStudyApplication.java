package com.zx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: myStudy
 * @description:
 * @author: zhou  xun
 * @create: 2023-07-04 15:29
 */
@SpringBootApplication
@EnableTransactionManagement  //开启事务
public class MyStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyStudyApplication.class, args);
    }
}
