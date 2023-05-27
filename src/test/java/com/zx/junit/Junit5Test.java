package com.zx.junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @program: myStudy
 * @description: Junit5框架学习测试
 * @author: zhou  xun
 * @create: 2023-05-28 01:04
 */
@DisplayName("Junit5 Test")
public class Junit5Test {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @DisplayName("Addition Test")
    @Test
    void testAddition() {
        assertEquals(2, calculator.add(1, 1));
    }

    @DisplayName("Subtraction Test")
    @Test
    void testSubtraction() {
        assertEquals(0, calculator.subtract(1, 1));
    }

    @DisplayName("Division Test")
    @Test
    void testDivision() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
    }

    @DisplayName("Multiplication Test")
    @Test
    void testMultiplication() {
        //assertAll 是 JUnit 5 中的一个断言方法，用于在一个测试方法中执行多个断言操作并收集所有的错误信息，如果有任何一个断言失败了，
        // 那么将会抛出一个 MultipleFailuresError 异常来表示所有的断言失败。这个方法的作用是可以让我们一次性地检查多个条件，
        // 而不需要在每个条件后面单独写一个 assert 方法，从而提高测试代码的可读性和可维护性。
        assertAll(
                () -> assertEquals(0, calculator.multiply(1, 0)),
                () -> assertEquals(1, calculator.multiply(1, 1)),
                () -> assertEquals(-6, calculator.multiply(2, -3))
        );
    }
//  注解
//    @Test：标记测试方法。
//    @BeforeEach：在每次测试之前运行的方法。
//    @AfterEach：在每次测试之后运行的方法。
//    @BeforeAll：在测试类中的所有测试之前运行的方法。
//    @AfterAll：在测试类中的所有测试之后运行的方法。
//    @DisplayName：为测试类或测试方法设置自定义显示名称。
//    @Disabled：禁用测试类或测试方法。
//    断言
//    assertArrayEquals()：比较两个数组是否相等。
//    assertEquals()：比较两个对象是否相等。
//    assertFalse()：验证条件是否为false。
//    assertNotNull()：验证对象是否不为null。
//    assertNotSame()：验证两个对象引用是否不同。
//    assertNull()：验证对象是否为null。
//    assertSame()：验证两个对象引用是否相同。
//    assertTrue()：验证条件是否为true。
//    assertThrows()：验证是否抛出了特定类型的异常。

}
