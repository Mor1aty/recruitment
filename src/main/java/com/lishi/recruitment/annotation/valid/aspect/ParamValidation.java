package com.lishi.recruitment.annotation.valid.aspect;

import java.lang.annotation.*;

/**
 * @author LiShi
 * date: 2020/12/31 14:26
 * description: ParamValidation 注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamValidation {
    Param[] value() default {};
}



