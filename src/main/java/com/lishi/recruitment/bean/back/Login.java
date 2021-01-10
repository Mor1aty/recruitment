package com.lishi.recruitment.bean.back;

import lombok.Data;

/**
 * @author LiShi
 * date: 2021/1/10 15:41
 * description: 登录返回
 */
@Data
public class Login {
    private String tokenCode;
    private String account;
    private Object info;
}
