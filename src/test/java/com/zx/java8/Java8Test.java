package com.zx.java8;


import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @program: myStudy
 * @description:
 * @author: zhou  xun
 * @create: 2023-05-21 14:24
 */
public class Java8Test {
    @Test
    public void testLambda() {
        // 使用 Lambda 表达式简化代码
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = numbers.stream().
                map(n -> n * n).
                collect(Collectors.toList());
        assertEquals(5, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(4), result.get(1));
        assertEquals(Integer.valueOf(9), result.get(2));
        assertEquals(Integer.valueOf(16), result.get(3));
        assertEquals(Integer.valueOf(25), result.get(4));
    }

    @Test
    public void testStream() {
        // 使用 Stream API 处理集合数据
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        List<String> result = words.stream().filter(word -> word.startsWith("a")).collect(Collectors.toList());
        assertEquals(1, result.size());
        assertEquals("apple", result.get(0));
    }

    @Test
    public void testOptional() {
        // 使用 Optional 类避免空指针异常

        // 使用 of() 创建一个包含非空值的 Optional
        Optional<String> optional = Optional.of("Hello, World!");
        System.out.println(optional);

        // 使用 ofNullable() 创建一个包含可能为空的 Optional
        Optional<String> optionalNullable = Optional.ofNullable(null);
        System.out.println(optionalNullable);

        // 使用 ofNullable() 创建一个包含可能为空的 Optional
        String str = "Hello, World!";
        Optional<String> optionalWithNullableValue = Optional.ofNullable(str);
        System.out.println(optionalWithNullableValue);
        //region java 9 特性
        // 使用 or() 返回第一个非空参数，如果所有参数都为空，返回默认值
        System.out.println(Optional.ofNullable(null).or(() -> Optional.of("Default Value")));

        // 使用 or() 返回第一个非空参数，如果所有参数都为空，返回默认值
        System.out.println(Optional.empty().or(() -> Optional.of("Default Value")));
        //endregion
        // 使用 orElse() 返回第一个非空参数，如果所有参数都为空，返回默认值
        System.out.println(Optional.empty().orElse("Default Value"));

        // 使用 ifPresent() 对值进行操作，如果值存在的话
        Optional<String> optionalIfPresent = Optional.of("Hello, World!");
        optionalIfPresent.ifPresent(System.out::println);

        // 使用 filter() 过滤出满足条件的 Optional
        Optional<String> optionalFilter = Optional.of("Hello, World!").filter(s -> s.startsWith("H"));
        System.out.println(optionalFilter);
    }

    @Test
    public void testOptionalofNullable() {
        JSONObject jsonObject = new JSONObject();
        // Optional.ofNullable(null) 等价于 Optional.empty()
        jsonObject.put("a", Optional.empty());
        jsonObject.put("b", Optional.ofNullable(null).orElse(""));
        jsonObject.put("c", Optional.ofNullable("1").orElse(""));
        System.out.println(jsonObject);
    }

    @Test
    public void testDateTime() {
        // 使用新的日期和时间 API
        LocalDate date = LocalDate.of(2021, Month.JANUARY, 1);
        assertEquals(2021, date.getYear());
        assertEquals(Month.JANUARY, date.getMonth());
        assertEquals(1, date.getDayOfMonth());
        assertEquals("2021-01-01", date.toString());

        LocalTime time = LocalTime.of(12, 30, 0);
        assertEquals(12, time.getHour());
        assertEquals(30, time.getMinute());
        assertEquals(0, time.getSecond());
        assertEquals("12:30:00", time.toString());

        LocalDateTime dateTime = LocalDateTime.of(date, time);
        assertEquals(date, dateTime.toLocalDate());
        assertEquals(time, dateTime.toLocalTime());
        assertEquals("2021-01-01T12:30:00", dateTime.toString());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsedDateTime = LocalDateTime.parse("2021-01-01 12:30:00", formatter);
        assertEquals(dateTime, parsedDateTime);
    }

    @Test
    public void testDefaultMethod() {
        // 接口可以定义默认方法
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        int length = words.stream().mapToInt(String::length).sum();
        assertEquals(20, length);
    }

    @Test
    public void testMethodReference() {
        // 方法引用可以简化代码
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        Function<String, Integer> lengthFunction = String::length;
        List<Integer> lengths = words.stream().map(lengthFunction).collect(Collectors.toList());
        assertEquals(Arrays.asList(5, 6, 6, 4), lengths);
    }

    @Test
    public void testParallelStream() {
        // 并行流可以提高处理效率
        List<Integer> numbers = Stream.iterate(1, n -> n + 1).limit(1000000).collect(Collectors.toList());
        long start = System.currentTimeMillis();
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        long end = System.currentTimeMillis();
        System.out.println("Sequential Stream: " + (end - start) + "ms, sum: " + sum);

        start = System.currentTimeMillis();
        sum = numbers.parallelStream().mapToInt(Integer::intValue).sum();
        end = System.currentTimeMillis();
        System.out.println("Parallel Stream: " + (end - start) + "ms, sum: " + sum);
    }
}
