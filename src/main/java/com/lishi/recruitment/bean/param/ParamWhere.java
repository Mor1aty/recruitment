package com.lishi.recruitment.bean.param;

import lombok.Data;

/**
 * @author LiShi
 * date: 2021/2/19 10:35
 * description:  where
 */
@Data
public class ParamWhere {
    private String key;
    private String opt;
    private String value;
    private String next;
}
