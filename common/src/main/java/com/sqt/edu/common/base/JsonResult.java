package com.sqt.edu.common.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description: 统一返回结果
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-04 11:17
 */
@Setter
@Getter
public class JsonResult<T> implements Serializable {

    /**
     * 返回数据
     */
    private T data;
    /**
     * 返回状态码
     */
    private int code;
    /**
     *信息
     */
    private String message;
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 构造函数
     */
    public JsonResult() {
        this.data = null;
        this.message = ResultCode.SUCCESS.getMessage();
        this.code = ResultCode.SUCCESS.getCode();
        this.success = (this.code == 200) ? true : false;
    }

    public JsonResult(T data) {
        this.data = data;
        this.message = ResultCode.SUCCESS.getMessage();
        this.code = ResultCode.SUCCESS.getCode();
        this.success = (this.code == 200) ? true : false;
    }

    public JsonResult(ResultCode resultCode) {
        this.data = null;
        this.message = resultCode.getMessage();
        this.code = resultCode.getCode();
        this.success = (this.code == 200) ? true : false;
    }

    public JsonResult(ResultCode resultCode,String message) {
        this.data = null;
        this.message = message;
        this.code = resultCode.getCode();
        this.success = (this.code == 200) ? true : false;
    }

    public JsonResult(int code, String message) {
        this.data = null;
        this.message = message;
        this.code = code;
        this.success = (this.code == 200) ? true : false;
    }

    public JsonResult(int code, String message, T data) {
        this.data = data;
        this.message = message;
        this.code = code;
        this.success = (this.code == 200) ? true : false;
    }

}
