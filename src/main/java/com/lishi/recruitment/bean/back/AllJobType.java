package com.lishi.recruitment.bean.back;

import com.lishi.recruitment.bean.db.JobType;
import lombok.Data;

import java.util.List;

/**
 * @author LiShi
 * date: 2021/4/3 19:24
 * description: 获取职位类型返回
 */
@Data
public class AllJobType {
    private int count;
    private List<JobType> jobTypes;
}
