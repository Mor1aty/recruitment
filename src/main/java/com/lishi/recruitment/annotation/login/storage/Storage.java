package com.lishi.recruitment.annotation.login.storage;

import java.util.HashMap;

/**
 * @author LiShi
 * date: 2020/12/31 14:22
 * description: Token 存储集合
 */
public class Storage {
    private static final HashMap<String, Token> STORAGE = new HashMap<>();

    /**
     * 存入 Token
     * @param tokenCode String
     * @param token Token
     */
    public static void put(String tokenCode, Token token) {
        STORAGE.put(tokenCode, token);
    }

    /**
     * 获取 Token
     * @param tokenCode String
     * @return Token
     */
    public static Token get(String tokenCode) {
        return STORAGE.get(tokenCode);
    }

    /**
     * 删除 Token
     * @param tokenCode String
     */
    public static void remove(String tokenCode) {
        STORAGE.remove(tokenCode);
    }
}
