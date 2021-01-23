package com.lishi.recruitment.bean.db;

import lombok.Data;

/**
 * @author LiShi
 * date: 2021/1/23 22:45
 * description: 期望职位
 */
@Data
public class ExpectedPosition {
    private long id;
    private String candidate;
    private String city;
    private String position;
    private String salary;
}
