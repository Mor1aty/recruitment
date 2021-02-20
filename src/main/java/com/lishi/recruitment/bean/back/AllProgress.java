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
    private long count;
    private List<BackProgress> progresses;
}
