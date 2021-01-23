package com.lishi.recruitment.bean.back;

import com.lishi.recruitment.bean.db.EducationExperience;
import com.lishi.recruitment.bean.db.ExpectedPosition;
import com.lishi.recruitment.bean.db.WorkExperience;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author LiShi
 * date: 2021/1/23 23:42
 * description: 应聘者信息
 */
@Data
public class CandidateInfo {
    private String account;
    private String name;
    private String gender;
    private String phone;
    private String email;
    private Date birthday;
    private String desc;
    private List<EducationExperience> educationExperienceList;
    private List<ExpectedPosition> expectedPositionList;
    private List<WorkExperience> workExperienceList;

}
