package com.lishi.recruitment.wrap;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author LiShi
 * date: 2020/12/31 11:51
 * description: 返回封装
 */
@Data   //自动生成get、set方法
@AllArgsConstructor
public class Wrapper<T> {
    private int code;
    private String msg;
    private T data;

}
