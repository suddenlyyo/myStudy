package com.zx.junit;


import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @program: myStudy
 * @description: junit4框架学习测试
 * @author: zhou  xun
 * @create: 2023-05-27 23:59
 */
public class Junit4Test {
    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @After
    public void tearDown() {
        calculator = null;
    }

    @BeforeClass
    public static void setUpClass() {
        // 设置测试环境
    }

    @AfterClass
    public static void tearDownClass() {
        // 清理测试环境
    }

    @Test
    public void testAdd() {
        // 测试方法
        int result = calculator.add(2, 3);
        assertEquals(5, result); // 期望值为5，实际值为result
    }

    @Test
    public void testSubtract() {
        // 测试方法
        int result = calculator.subtract(5, 3);
        assertTrue(result > 0); // 判断result是否大于0
    }

    @Test
    public void testException() {
        // 测试方法
        try {
            int a = 10;
            int b = a / 0;
        } catch (Exception e) {
            assertEquals("/ by zero", e.getMessage()); // 断言异常信息
        }
    }

    @Test
    @Ignore("Not implemented yet")
    public void testIgnore() {
        // 测试方法

    }

//    注解
//    @Test：标记测试方法。在测试类中，可以有多个@Test方法。
//    @Before：在每个@Test方法之前运行。用于设置测试环境。
//    @After：在每个@Test方法之后运行。用于清理测试环境。
//    @BeforeClass：在所有@Test方法之前运行。用于设置测试环境。 注释在执行任何测试方法之前运行一次，并且适用于所有测试方法
//    @AfterClass：在所有@Test方法之后运行。用于清理测试环境。注释在执行任何测试方法之后运行一次，并且适用于所有测试方法
//    @Ignore：标记测试方法为忽略。可以用于暂时禁用测试方法。
//    断言:
//    assertEquals：比较两个值是否相等。
//    assertTrue：判断一个条件是否为真。
//    assertFalse：判断一个条件是否为假。
//    assertNull：判断一个对象是否为null。
//    assertNotNull：判断一个对象是否不为null。
//    assertSame：判断两个对象是否是同一个对象。
//    assertNotSame：判断两个对象是否不是同一个对象。
//    assertThat：使用Hamcrest匹配器进行断言。

}
