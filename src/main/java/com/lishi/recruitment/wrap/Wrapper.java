package com.lishi.recruitment.wrap;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author LiShi
 * date: 2020/12/31 11:51
 * description: 返回封装
 */
@Data
@AllArgsConstructor
public class Wrapper<T> {
    private int code;
    private String msg;
    private T data;

}
