package com.lishi.recruitment.bean.param;

import lombok.Data;

import java.util.List;

/**
 * @author LiShi
 * date: 2021/2/19 10:29
 * description: Param Condition
 */
@Data
public class ParamCondition {
    private String order;  //正序还是倒序（给数据库用）
    private String orderBy;//根据某字段
    private int pageNum;//页码
    private int pageSize;//每页多少条
    private List<ParamWhere> wheres;//判断条件
}
