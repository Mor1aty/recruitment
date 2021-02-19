package com.lishi.recruitment.bean.back;

import lombok.Data;

import java.util.List;

/**
 * @author LiShi
 * date: 2021/2/19 11:48
 * description: 获取所有公司信息返回
 */
@Data
public class AllCompany {
    private long count;
    private List<BackCompany> companies;
}
