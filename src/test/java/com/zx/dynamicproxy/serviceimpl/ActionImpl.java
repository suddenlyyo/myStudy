package com.zx.dynamicproxy.serviceimpl;

import com.zx.dynamicproxy.service.Action;

/**
 * @program: myStudy
 * @description:
 * @author: zhou  xun
 * @create: 2023-08-17 11:28
 */
public class ActionImpl implements Action {
    @Override
    public String eat() {
        return "在吃了！";
    }
}
