package com.lishi.recruitment.annotation.login.utils;

import com.lishi.recruitment.utils.ValueUtils;
import com.lishi.recruitment.annotation.login.storage.Storage;
import com.lishi.recruitment.annotation.login.storage.Token;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author LiShi
 * date: 2020/12/31 14:25
 * description: Token 工具类
 */
public class TokenUtil {

    /**
     * 获取 request 的 Token
     * @param tokenCode String
     * @return Token
     */
    public static Token checkToken(String tokenCode) {
        Token token = Storage.get(tokenCode);
        if (ValueUtils.valEmpty(token)) {
            return null;
        }
        if(token.effectiveTime == -1){
            // 永久存储
            return token;
        }
        if (LocalDateTime.now().isAfter(token.generationTime.plusHours(token.effectiveTime))) {
            // Token 过期
            Storage.remove(tokenCode);
            return null;
        }
        return token;
    }

    /**
     * 存入 Token
     * @param token Token
     * @return String
     */
    public static String putTokenStorage(Token token) {
        token.generationTime = LocalDateTime.now();
        if (token.effectiveTime == 0) {
            token.effectiveTime = 5;
        }
        String tokenCode = UUID.randomUUID().toString().replaceAll("-", "");
        token.code = tokenCode;
        Storage.put(tokenCode, token);
        return tokenCode;
    }

    /**
     * 移除 Token
     * @param tokenCode String
     */
    public static void removeToken(String tokenCode) {
        Storage.remove(tokenCode);
    }
}
