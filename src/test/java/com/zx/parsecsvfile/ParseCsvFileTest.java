package com.zx.parsecsvfile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: myStudy
 * @description: 解析csv文件测试
 * @author: zhou  xun
 * @create: 2023-07-11 18:43
 */
public class ParseCsvFileTest {
    public static void main(String[] args) {
        List<String[]> data = new ArrayList<>();
        String filePath = System.getProperty("user.dir") +
                File.separator + "src" + File.separator + "test" + File.separator
                + "resources" + File.separator + "test.csv"; // CSV文件路径
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath));
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(","); // 使用逗号作为分隔符
                data.add(values);
            }
            List<String> collect = data.stream()
                    .map(arr -> String.join(",", arr)).collect(Collectors.toList());
            collect.forEach(System.out::println);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }
}
