package com.sqt.edu.core.base;

/**
 * 基本异常信息定义
 *
 * @author xs
 */
public enum BaseExceptionMsg implements IExceptionMsg {

    EXECUTE_OK(0, "执行成功"),
    QUERY_OK(0, "查询成功"),
    EXECUTE_FAILD(-1, "执行失败"),

    AUTH_IDCARD_FAIL(10000, "身份证认证失败"),
    IDCARD_OCR_FAIL(10001, "证件识别失败"),
    PARAM_INVALID(10002, "无效参数"),
    INTER_NOT_SURPPORT(10003, "接口未实现，暂不支持"),
    USER_NOT_PHONE(10004, "用户没有注册手机号"),
    VERIFICATIONCODE_IS_NULL(10005, "验证码不存在"),
    VERIFICATIONCODE_IS_EXPIRE(10006, "验证码过期"),
    VERIFICATION_ERROR(10007, "验证码错误次数已达上限,请稍后重试"),
    VERIFICATIONCODE_IS_ERROR(10008, "验证码错误"),
    DIC_NOT_EXSITS(10009, "字典项不存在"),
    SPRINGCONTEXTHELPER_ERROR(10010, "Spring框架错误"),

    MICROSERVICENAME_NOT_EXIST(10011, "微服务不存在"),
    GET_RES_ERROR(10012, "获取信息失败,系统服务开小差"),
    PHONE_NOT_REGISTER(10013, "手机号没有注册"),
    DATA_EXIST(10014, "新增数据已存在,请勿重复操作"),
    OPERATION_NOT_SUPPORTED(10015, "不支持的操作"),

    USER_NOT_EXIST(10016, "用户不存在"),
    PHONE_NOT_EXIST(10017, "该密保手机号不存在"),
    USERNAME_NOT_MATCH_PHONE(10018, "密保手机和用户名不匹配"),

    REGISTER_USER_NOT_EXIST(10019, "该身份证号不存在或未认证！"),
    NAME_CARD_NOT_SAME(10020, "姓名和身份证信息不符！"),
    REGISTER_PHONE_NOT_EXIST(10021, "原手机号与身份证信息不符！"),
    UPDATE_PHONE_REGISTERED(10022, "您要修改的手机号已被认证,不支持修改！"),
    APPEAL_STATUS_AUDIT(10023, "当前账号申诉已在审核中！"),
    SEND_MEG_TOO_FAST(10024, "发送短信过于频繁，请稍后重试!"),
    USERNAME_NOT_REGISTERED(10025, "该用户名没有注册!"),

    CURRENT_THREAD_GET_LOCK_FAIL(10027, "当前线程获取锁失败!"),
    ENC_API_FAILED(10029, "加密请求失败，异常编码为152"),
    ;


    private Integer code;
    private String message;

    BaseExceptionMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
