package com.zx.java8;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @program: myStudy
 * @description:
 * @author: zhou  xun
 * @create: 2023-05-21 14:26
 */
public class StreamTest {
    @Test
    public void testFilter() {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        // 过滤出以字母 a 开头的单词
        List<String> result = words.stream().filter(word -> word.startsWith("a")).collect(Collectors.toList());
        assertEquals(1, result.size());
        assertEquals("apple", result.get(0));
    }

    @Test
    public void testMap() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // 将每个数字平方
        List<Integer> squares = numbers.stream().map(n -> n * n).collect(Collectors.toList());
        assertEquals(5, squares.size());
        assertEquals(Integer.valueOf(1), squares.get(0));
        assertEquals(Integer.valueOf(4), squares.get(1));
        assertEquals(Integer.valueOf(9), squares.get(2));
        assertEquals(Integer.valueOf(16), squares.get(3));
        assertEquals(Integer.valueOf(25), squares.get(4));
    }

    @Test
    public void testFlatMap() {
        List<List<Integer>> numbers = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6));
        // 将二维数组转为一维数组
        List<Integer> result = numbers.stream().flatMap(List::stream).collect(Collectors.toList());
        assertEquals(6, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));
        assertEquals(Integer.valueOf(4), result.get(3));
        assertEquals(Integer.valueOf(5), result.get(4));
        assertEquals(Integer.valueOf(6), result.get(5));
    }

    @Test
    public void testSorted() {
        List<Integer> numbers = Arrays.asList(5, 3, 1, 4, 2);
        // 对数字进行排序
        List<Integer> result = numbers.stream().sorted().collect(Collectors.toList());
        Consumer<Integer> consumer = n -> System.out.println(n);
        result.forEach(consumer);
        assertEquals(5, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));
        assertEquals(Integer.valueOf(4), result.get(3));
        assertEquals(Integer.valueOf(5), result.get(4));
    }

    @Test
    public void testDistinct() {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        // 去除数字中的重复元素
        List<Integer> result = numbers.stream().distinct().collect(Collectors.toList());
        assertEquals(4, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));
        assertEquals(Integer.valueOf(4), result.get(3));
    }

    @Test
    public void testReduce() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // 对数字求和
        int sum = numbers.stream().reduce(0, Integer::sum);
        assertEquals(15, sum);
    }

    @Test
    public void testForEach() {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        // 输出每个单词
        words.stream().forEach(System.out::println);
    }
}
