package com.lishi.recruitment.bean.back;

import lombok.Data;

import java.util.List;

/**
 * @author LiShi
 * date: 2021/2/20 16:54
 * description: 获取所有招聘进度返回
 */
@Data
public class AllProgress {
    private long count;//多少个
    private List<BackProgress> progresses;//当前页有多少个
}
