package com.lishi.recruitment.annotation.valid.aspect;


import com.lishi.recruitment.annotation.valid.bean.Method;

import java.lang.annotation.*;

/**
 * @author LiShi
 * date: 2020/12/31 14:25
 * description: Param 注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Param{
    String value();
    Method[] method() default Method.NOTNULL;
}
