package com.zx.modle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: myStudy
 * @description: 测试对象
 * @author: zhou  xun
 * @create: 2022-09-21 15:27
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    private  String name;
    private  Integer sex;
}
