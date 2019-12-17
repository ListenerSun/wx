package com.sqt.edu.core.exception;

import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.base.JsonResult;
import com.sqt.edu.core.base.ResultCode;
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
 *
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionTranslator {


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonResult handleError(MissingServletRequestParameterException e) {
        log.warn("Missing Request Parameter", e);
        String message = String.format("Missing Request Parameter: %s", e.getParameterName());
        return new JsonResult(ResultCode.PARAM_MISS);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public JsonResult handleError(MethodArgumentTypeMismatchException e) {
        log.warn("Method Argument Type Mismatch", e);
        String message = String.format("Method Argument Type Mismatch: %s", e.getName());
        return new JsonResult(ResultCode.PARAM_TYPE_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult handleError(MethodArgumentNotValidException e) {
        log.warn("Method Argument Not Valid", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return new JsonResult(ResultCode.PARAM_VALID_ERROR);
    }

    @ExceptionHandler(BindException.class)
    public JsonResult handleError(BindException e) {
        log.warn("Bind Exception", e);
        FieldError error = e.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return new JsonResult(ResultCode.PARAM_BIND_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult handleError(ConstraintViolationException e) {
        log.warn("Constraint Violation", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());
        return new JsonResult(ResultCode.PARAM_VALID_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public JsonResult handleError(NoHandlerFoundException e) {
        log.error("404 Not Found", e);
        return new JsonResult(ResultCode.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonResult handleError(HttpMessageNotReadableException e) {
        log.error("Message Not Readable", e);
        return new JsonResult(ResultCode.MSG_NOT_READABLE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult handleError(HttpRequestMethodNotSupportedException e) {
        log.error("Request Method Not Supported", e);
        return new JsonResult(ResultCode.METHOD_NOT_SUPPORTED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public JsonResult handleError(HttpMediaTypeNotSupportedException e) {
        log.error("Media Type Not Supported", e);
        return new JsonResult(ResultCode.MEDIA_TYPE_NOT_SUPPORTED);

    }

    @ExceptionHandler(ServiceException.class)
    public JsonResult handleError(ServiceException e) {
        log.error("Service Exception", e);
        return new JsonResult(e.getResultCode().getCode(),e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public JsonResult handleError(Throwable e) {
        log.error("Internal Server Error", e);
        return new JsonResult(ResultCode.INTERNAL_SERVER_ERROR);
    }
}
