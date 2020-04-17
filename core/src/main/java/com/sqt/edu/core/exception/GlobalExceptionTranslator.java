package com.sqt.edu.core.exception;

import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.common.base.ResultCode;
import com.sqt.edu.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @Description: 全局异常处理器
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-16 11:08
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionTranslator {


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonResult handleError(MissingServletRequestParameterException e) {
        log.warn("==========>Missing Request Parameter", e);
        return new JsonResult(ResultCode.PARAM_MISS);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public JsonResult handleError(MethodArgumentTypeMismatchException e) {
        log.warn("==========>Method Argument Type Mismatch", e);
        return new JsonResult(ResultCode.PARAM_TYPE_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult handleError(MethodArgumentNotValidException e) {
        log.warn("==========>Method Argument Not Valid", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        return new JsonResult(ResultCode.FAILURE.getCode(),e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(BindException.class)
    public JsonResult handleError(BindException e) {
        log.warn("==========>Bind Exception", e);
        FieldError error = e.getFieldError();
        return new JsonResult(ResultCode.PARAM_BIND_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult handleError(ConstraintViolationException e) {
        log.warn("==========>Constraint Violation", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        return new JsonResult(ResultCode.PARAM_VALID_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public JsonResult handleError(NoHandlerFoundException e) {
        log.error("==========>404 Not Found", e);
        return new JsonResult(ResultCode.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonResult handleError(HttpMessageNotReadableException e) {
        log.error("==========>Message Not Readable", e);
        return new JsonResult(ResultCode.MSG_NOT_READABLE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult handleError(HttpRequestMethodNotSupportedException e) {
        log.error("==========>Request Method Not Supported", e);
        return new JsonResult(ResultCode.METHOD_NOT_SUPPORTED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public JsonResult handleError(HttpMediaTypeNotSupportedException e) {
        log.error("==========>Media Type Not Supported", e);
        return new JsonResult(ResultCode.MEDIA_TYPE_NOT_SUPPORTED);

    }
    @ExceptionHandler(IllegalArgumentException.class)
    public JsonResult handleError(IllegalArgumentException e) {
        log.error("==========>IllegalArgumentException:", e);
        return new JsonResult(ResultCode.PARAM_VALID_ERROR.getCode(),e.getMessage());

    }

    @ExceptionHandler(ServiceException.class)
    public JsonResult handleError(ServiceException e) {
        log.error("==========>Service Exception", e);
        return new JsonResult(e.getResultCode().getCode(),e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public JsonResult handleError(Throwable e) {
        log.error("==========>Internal Server Error", e);
        return new JsonResult(ResultCode.INTERNAL_SERVER_ERROR);
    }
}
