package com.lishi.recruitment.annotation.valid.bean;
/**
 * @author LiShi
 * date: 2020/12/31 14:34
 * description: Param 注解 method 枚举
 */
public enum Method {
    /**
     * 邮箱检测
     */
    EMAIL,

    /**
     * 非空检测
     */
    NOTNULL,

    /**
     * 数字检测
     */
    NUMBER,

    /**
     * 手机检测
     */
    PHONE,
}
