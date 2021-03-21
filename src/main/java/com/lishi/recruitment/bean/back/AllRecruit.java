package com.lishi.recruitment.bean.back;

import lombok.Data;

import java.util.List;

/**
 * @author LiShi
 * date: 2021/2/19 10:08
 * description: 获取所有招聘信息返回
 */
@Data
public class AllRecruit {
    private long count;
    private List<BackJob> jobs;//分页用的，当前页显示多少个
}
