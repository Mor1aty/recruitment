package com.lishi.recruitment.bean.param;

import lombok.Data;

/**
 * @author LiShi
 * date: 2021/2/19 10:35
 * description: 获取所有招聘 where
 */
@Data
public class FindRecruitWhere {
    private String key;
    private String opt;
    private String value;
    private String next;
}
