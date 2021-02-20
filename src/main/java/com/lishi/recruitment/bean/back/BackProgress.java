package com.lishi.recruitment.bean.back;

import com.lishi.recruitment.bean.db.Candidate;
import lombok.Data;

/**
 * @author LiShi
 * date: 2021/2/20 16:57
 * description: 招聘进度返回
 */
@Data
public class BackProgress {
    private long id;
    private long job;
    private String jobName;
    private String candidateAccount;
    private String candidateName;
    private String candidateGender;
    private int progress;
}
