package com.sqt.edu.common.base;

/**
 * @Description: 返回结果处理器
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-23 9:57
 */
public final class JsonResultHandler {

    /**处理成功请求
     * @param data
     * @return
     */
    public static JsonResult success(Object data){
        return new JsonResult(data);
    }

    /**
     * @return
     */
    public static JsonResult success(){
        return new JsonResult();
    }

    /**处理失败请求
     * @param resultCode
     * @return
     */
    public static JsonResult failure(ResultCode resultCode){
        return new JsonResult(resultCode);
    }

}
