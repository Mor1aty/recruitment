package com.lishi.recruitment.bean.db;

import lombok.Data;

import java.util.Date;

/**
 * @author LiShi
 * date: 2021/1/23 22:48
 * description: 教育经历
 */
@Data
public class EducationExperience {
    private long id;
    private String candidate;
    private String school;
    private String academic;
    private String major;
    private Date startDate;
    private Date endDate;
    private String desc;
}
