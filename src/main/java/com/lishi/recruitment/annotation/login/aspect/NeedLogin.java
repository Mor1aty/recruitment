package com.lishi.recruitment.annotation.login.aspect;

import java.lang.annotation.*;

/**
 * @author LiShi
 * date: 2020/12/31 14:21
 * description: NeedLogin 注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedLogin {
    String value() default "token";
    long level() default -1;
}
