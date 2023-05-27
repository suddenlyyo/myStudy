package com.zx.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @program: myStudy
 * @description: 空指针判断测试
 * @author: zhou  xun
 * @create: 2022-09-21 09:27
 */
@Slf4j
public class NullPointerExceptionTest {
    /**
     * 引用类型：直接使用null判断空指针
     */
    @Test
    public void nullRawTest() {
        // Optional 类示例
        String str = "";
        Optional<String> optionalStr = Optional.ofNullable(str);
        System.out.println(optionalStr.isPresent());
        System.out.println(optionalStr.orElse("default"));

    }
    /**
     * 引用类型：使用Objects工具判断空指针
     */
    @Test
    public void nullUnderObjectsTest() {
        String var1 = null;
        if (Objects.isNull(var1)) {
            log.info(">>>>>>>>>>var1 is null");
        } else {
            log.info(">>>>>>>>>>var1 is:{}", var1);
        }
    }

    /**
     * 集合类型：直接使用null判断空指针
     */
    @Test
    public void nullCollectionUnderRawTest() {
        List<String> list1 = null;
        if (null == list1) {
            log.info(">>>>>>>>>>list1 is null");
        } else if (list1.isEmpty()) {
            log.info(">>>>>>>>>>list1 is empty");
        } else {
            log.info(">>>>>>>>>>list1 is:{}", list1);
        }
    }

    /**
     * 集合类型：使用Objects判断空指针
     */
    @Test
    public void nullCollectionUnderObjectsTest() {
        List<String> list1 = null;
        if (Objects.isNull(list1)) {
            log.info(">>>>>>>>>>list1 is null");
        } else if (list1.isEmpty()) {
            log.info(">>>>>>>>>>list1 is empty");
        } else {
            log.info(">>>>>>>>>>list1 is:{}", list1);
        }
    }

    /**
     * 集合类型：使用CollectionUtils判断空指针和空数据
     */
    @Test
    public void nullUnderCollectionUtilsTest() {
        List<String> list1 = null;
        if (CollectionUtils.isEmpty(list1)) {
            log.info(">>>>>>>>>>var1 is null or empty");
        } else {
            log.info(">>>>>>>>>>var1 is:{}", list1);
        }
    }

    /**
     * 集合类型：使用CollectionUtils判断空指针和空数据
     */
    @Test
    public void emptyUnderCollectionUtilsTest() {
        List<String> list1 = Collections.emptyList();
        if (CollectionUtils.isEmpty(list1)) {
            log.info(">>>>>>>>>>var1 is null or empty");
        } else {
            log.info(">>>>>>>>>>var1 is:{}", list1);
        }
    }

}
