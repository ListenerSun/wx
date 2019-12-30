package com.sqt.edu.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Description: 身份证号校验注解
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-30 19:45
 */
@Documented
@Constraint(validatedBy = IdCardValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IdCard {

    String message() default "Invalid IdCard number";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
