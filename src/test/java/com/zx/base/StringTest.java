package com.zx.base;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: myStudy
 * @description:
 * @author: zhou  xun
 * @create: 2023-10-24 11:41
 */
public class StringTest {
    @Test
    public void stringJoin() {
        List<String> bill = Stream.of("1", "2", "3")
                .collect(Collectors.toList());
        String join = String.join(",", bill);
        System.out.println(join);
    }

    @Test
    public void stringFormat() {
        //%d：用于格式化十进制整数。
        //%f：用于格式化浮点数。
        //%s：用于格式化字符串。
        //%c：用于格式化字符。
        //%%：用于插入百分号（%）字符。
        String formattedString = String.format("数字是 %d, 浮点数是 %f,字符串是 %s,字符是 %c,百分号是 %%", 123, 3.14, "Hello", 'A', 100);
        System.out.println(formattedString);
    }

    @Test
    public void stringSplit() {
        String str = "1,2,3,4,5,6,7,8";
        String[] split = str.split(",");
        if (split.length > 0) {
            for (int compared = 0; compared < split.length; compared++) {
                System.out.printf("index is %d,value is %s%n",compared,split[compared]);
            }
        }
    }
}
