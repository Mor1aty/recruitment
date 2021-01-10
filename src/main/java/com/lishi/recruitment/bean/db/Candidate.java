package com.lishi.recruitment.bean.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @author LiShi
 * date: 2021/1/9 18:28
 * description: 应聘者
 */
@Data
public class Candidate {
    private String account;

    @JsonIgnore
    private String password;
    private String name;
    private String gender;
    private String phone;
    private String email;
    private Date birthday;
    private String desc;
}
