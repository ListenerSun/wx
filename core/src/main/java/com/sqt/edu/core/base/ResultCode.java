package com.sqt.edu.core.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

/**
 * Result Code Enum
 *
 * @author william
 */
@Getter
@AllArgsConstructor
public enum ResultCode {


    /************************************************************************************************************/
    /*****************************************************公共异常***********************************************/

    SUCCESS(HttpServletResponse.SC_OK, "Operation is Successful"),

    FAILURE(HttpServletResponse.SC_BAD_REQUEST, "Biz Exception"),

    UN_AUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "Request Unauthorized"),

    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "404 Not Found"),

    MSG_NOT_READABLE(HttpServletResponse.SC_BAD_REQUEST, "Message Can't be Read"),

    METHOD_NOT_SUPPORTED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method Not Supported"),

    MEDIA_TYPE_NOT_SUPPORTED(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Media Type Not Supported"),

    REQ_REJECT(HttpServletResponse.SC_FORBIDDEN, "Request Rejected"),

    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error"),

    PARAM_MISS(HttpServletResponse.SC_BAD_REQUEST, "Missing Required Parameter"),

    PARAM_TYPE_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Type Mismatch"),

    PARAM_BIND_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Binding Error"),

    PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Validation Error"),

    /************************************************************************************************************/
    /**********************************自定义公共异常:9900-10000 以CORE_开头**************************************/
    CORE_PARAM_INVALID(9900, "无效参数"),
    CORE_SPRINGCONTEXTHELPER_ERROR(9901, "Spring框架错误"),



    /************************************************************************************************************/
    /**************************************用户相关异常:10001-10100 以USER_开头************************************/
    USER_NOT_EXIST(10001,"user not exist！"),
    USER_PASSWORD_ERROR(10002,"user password error！"),
    USER_NOT_AUTH(10003,"用户未实名认证！"),




    /************************************************************************************************************/
    /**************************************服务调用相关异常:10101-10200 以MIC_开头**********************************/
    MIC_SERVICE_EXCEPTION(10101,"微服务 FeignClient 调用异常 ！"),


    /************************************************************************************************************/
    /**************************************老师服务相关异常:10201-10300 以TEA_开头**********************************/

    TEA_NOT_TEACHER(10201,""),


    /************************************************************************************************************/
    /**************************************课程服务相关异常:10301-10400 以TEA_开头**********************************/
    COURSE_NOT_EXIST(10301,"课程信息不存在!"),

    /************************************************************************************************************/
    /**************************************安全服务相关异常:10401-10500 以AUTH_开头**********************************/
    AUTH_TOKEN_ERROR(10401,"acess_token无效!")

    ;

    final int code;

    final String message;
}
