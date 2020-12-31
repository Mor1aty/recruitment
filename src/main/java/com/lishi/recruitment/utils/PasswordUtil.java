package com.lishi.recruitment.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author LiShi
 * date: 2020/12/31 15:07
 * description: 密码工具类
 */
public class PasswordUtil {

    /**
     * md5 加密
     *
     * @param password String
     * @return String
     */
    public static String encrypt(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] s = md.digest();
        StringBuilder result = new StringBuilder();
        for (byte b : s) {
            result.append(Integer.toHexString((0x000000FF & b) | 0xFFFFFF00).substring(6));
        }
        return result.toString();
    }
}
