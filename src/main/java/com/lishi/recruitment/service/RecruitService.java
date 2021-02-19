package com.lishi.recruitment.service;

import com.lishi.recruitment.bean.back.AllCompany;
import com.lishi.recruitment.bean.back.AllRecruit;
import com.lishi.recruitment.bean.param.ParamCondition;
import com.lishi.recruitment.bean.param.ParamWhere;
import com.lishi.recruitment.mapper.RecruitMapper;
import com.lishi.recruitment.utils.ValueUtils;
import com.lishi.recruitment.wrap.WrapMapper;
import com.lishi.recruitment.wrap.Wrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiShi
 * date: 2021/2/19 10:11
 * description: 招聘 Service
 */
@Service
@AllArgsConstructor
public class RecruitService {

    private final RecruitMapper recruitMapper;

    /**
     * 根据条件获取所有招聘信息
     *
     * @param findRecruit FindRecruit
     * @return Wrapper<AllRecruit>
     */
    public Wrapper<AllRecruit> findRecruitByCondition(ParamCondition findRecruit) {
        List<ParamWhere> wheres = findRecruit.getWheres();
        StringBuilder condition = new StringBuilder();
        String limit = "";
        if (wheres != null && !wheres.isEmpty()) {
            condition.append("where ");
            for (ParamWhere where : wheres) {
                condition.append(where.getKey()).append(" ").append(where.getOpt()).append(" '").
                        append(where.getValue()).append("' ").append(where.getNext()).append(" ");
            }
        }
        if (ValueUtils.valNotEmpty(findRecruit.getOrder()) && ValueUtils.valNotEmpty(findRecruit.getOrderBy())) {
            condition.append("order by `").append(findRecruit.getOrderBy()).append("` ").
                    append(findRecruit.getOrder()).append(" ");
        }
        if (findRecruit.getPageNum() > 0 && findRecruit.getPageSize() > 0) {
            int pageNum = (findRecruit.getPageNum() - 1) * findRecruit.getPageSize();
            limit = "limit " + pageNum + ", " + findRecruit.getPageSize() + " ";
        }

        AllRecruit allRecruit = new AllRecruit();
        allRecruit.setJobs(recruitMapper.findRecruitByCondition(condition.toString() + limit));
        allRecruit.setCount(recruitMapper.findRecruitCountByCondition(condition.toString()));

        return WrapMapper.okObtain("获取成功", allRecruit);
    }

    /**
     * 根据条件获取公司信息
     *
     * @param findRecruit FindRecruit
     * @return Wrapper<AllCompany>
     */
    public Wrapper<AllCompany> findCompanyByCondition(ParamCondition findRecruit) {
        List<ParamWhere> wheres = findRecruit.getWheres();
        StringBuilder condition = new StringBuilder();
        String limit = "";
        if (wheres != null && !wheres.isEmpty()) {
            condition.append("where ");
            for (ParamWhere where : wheres) {
                condition.append(where.getKey()).append(" ").append(where.getOpt()).append(" '").
                        append(where.getValue()).append("' ").append(where.getNext()).append(" ");
            }
        }
        if (ValueUtils.valNotEmpty(findRecruit.getOrder()) && ValueUtils.valNotEmpty(findRecruit.getOrderBy())) {
            condition.append("order by `").append(findRecruit.getOrderBy()).append("` ").
                    append(findRecruit.getOrder()).append(" ");
        }
        if (findRecruit.getPageNum() > 0 && findRecruit.getPageSize() > 0) {
            int pageNum = (findRecruit.getPageNum() - 1) * findRecruit.getPageSize();
            limit = "limit " + pageNum + ", " + findRecruit.getPageSize() + " ";
        }


        return null;
    }
}
