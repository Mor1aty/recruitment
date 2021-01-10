package com.lishi.recruitment.bean;

import com.lishi.recruitment.annotation.login.storage.Token;
import lombok.Data;

/**
 * @author LiShi
 * date: 2021/1/10 15:34
 * description: 用户的 Token
 */
@Data
public class UserToken extends Token {
    private String account;
}
