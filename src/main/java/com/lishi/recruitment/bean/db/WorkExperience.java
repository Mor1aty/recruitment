package com.lishi.recruitment.bean.db;

import lombok.Data;

/**
 * @author LiShi
 * date: 2021/1/23 22:47
 * description: 工作经历
 */
@Data
public class WorkExperience {
    private long id;
    private String candidate;
    private String company;
    private String department;
    private String position;
    private String desc;
}
