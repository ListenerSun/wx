package com.sqt.edu.common.base;

/**

 *
 * @author sqt
 * @date 19-11-13
 */
public interface IExceptionMsg {

    /**
     * 异常的状态码
     *
     * @return
     */
    Integer getCode();

    /**
     * 异常的提示信息
     *
     * @return
     */
    String getMessage();
}
