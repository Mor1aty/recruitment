package com.lishi.recruitment.bean.back;

import com.lishi.recruitment.bean.db.Candidate;
import lombok.Data;

import java.util.List;

/**
 * @author Moriaty
 * date: 2021/4/3 18:36
 * description: 获取所有应聘者返回
 */
@Data
public class AllCandidate {
    private int count;
    private List<Candidate> candidates;
}
