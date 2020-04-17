package com.sqt.edu.common.base;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description: 自定义分页返回对象
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-17 17:32
 */
public final class PageResult<T> extends PageInfo<T> {

    public PageResult(List<T> list) {
        super(list);
    }
}
