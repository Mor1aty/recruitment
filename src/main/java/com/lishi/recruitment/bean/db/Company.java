package com.lishi.recruitment.bean.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author LiShi
 * date: 2021/1/9 18:36
 * description: 公司
 */
@Data
public class Company {
    private String account;

    @JsonIgnore
    private String password;
    private String name;
    private String city;
    private String website;
    private String code;
    private String desc;
}
