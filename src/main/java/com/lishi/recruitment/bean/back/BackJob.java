package com.lishi.recruitment.bean.back;

import lombok.Data;


/**
 * @author LiShi
 * date: 2021/2/19 11:14
 * description: 返回的工作
 */
@Data
public class BackJob {
    private long id;
    private String name;
    private long type;
    private String typeName;
    private String company;
    private String companyName;
    private String desc;
    private int minSalary;
    private int maxSalary;
    private String city;
}
