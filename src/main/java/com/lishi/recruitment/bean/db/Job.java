package com.lishi.recruitment.bean.db;

import lombok.Data;

/**
 * @author LiShi
 * date: 2021/2/19 10:03
 * description: 工作表
 */
@Data
public class Job {
    private long id;
    private String name;
    private long type;
    private String company;
    private String desc;
    private int minSalary;
    private int maxSalary;
    private String city;

}
