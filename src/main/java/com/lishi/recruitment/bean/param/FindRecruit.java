package com.lishi.recruitment.bean.param;

import lombok.Data;

import java.util.List;

/**
 * @author LiShi
 * date: 2021/2/19 10:29
 * description: 获取所有招聘 Param
 */
@Data
public class FindRecruit {
    private String order;
    private String orderBy;
    private int pageNum;
    private int pageSize;
    private List<FindRecruitWhere> wheres;
}
