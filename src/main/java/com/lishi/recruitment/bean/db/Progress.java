package com.lishi.recruitment.bean.db;

import lombok.Data;

/**
 * @author LiShi
 * date: 2021/2/20 17:39
 * description: 招聘进度表
 */
@Data
public class Progress {
    private long id;
    private long job;
    private String candidate;
    private int progress;
}
