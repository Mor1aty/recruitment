package com.lishi.recruitment.bean.param;

import lombok.Data;

/**
 * @author LiShi
 * date: 2021/2/19 10:35
 * description:  where
 */
@Data //自动生成get、set方法
public class ParamWhere {
    private String key;//键
    private String opt;//操作><=like
    private String value;//值
    private String next;//下一个
}
