package com.zx.base;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: myStudy
 * @description: map 遍历
 * @author: zhou  xun
 * @create: 2023-10-17 13:19
 */
public class MapTest {
    @Test
    public void keySet() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
//        for (String key : map.keySet()) {
//            System.out.println("Key: " + key + ", Value: " + map.get(key));
//        }
        map.keySet().stream().map(key -> "Key: " + key + ", Value: " + map.get(key)).forEach(System.out::println);
    }

    @Test
    public void entrySet() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//        }
        map.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
    }

    @Test
    public void values() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
//        for (Integer value : map.values()) {
//            System.out.println("Value: " + value);
//        }
        map.values().stream().map(value -> "Value: " + value).forEach(System.out::println);
    }
}
