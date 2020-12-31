package com.lishi.recruitment.annotation.login.storage;

import java.time.LocalDateTime;

/**
 * @author LiShi
 * date: 2020/12/31 14:24
 * description: Token bean
 */
public class Token {
    /**
     * token code
     */
    public String code;

    /**
     * 有效时间，单位:时，-1 为永久存储
     */
    public long effectiveTime;

    /**
     * 生成时间
     */
    public LocalDateTime generationTime;

    /**
     * 权限等级
     */
    public int level;

}
