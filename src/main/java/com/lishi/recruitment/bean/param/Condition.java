package com.lishi.recruitment.bean.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiShi
 * date: 2021/2/19 18:35
 * description: 条件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Condition {
    private String condition;//不带分页的查询条件
    private String conditionWithLimit;//带分页的查询条件

}
