package com.lishi.recruitment.bean.back;

import lombok.Data;

/**
 * @author LiShi
 * date: 2021/2/19 11:49
 * description: 公司信息返回
 */
@Data
public class BackCompany {
    private String account;
    private String name;
    private String city;
    private String website;
    private String desc;
}
