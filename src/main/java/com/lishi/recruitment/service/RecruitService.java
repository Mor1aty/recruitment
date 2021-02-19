package com.lishi.recruitment.service;

import com.lishi.recruitment.bean.back.AllCompany;
import com.lishi.recruitment.bean.back.AllRecruit;
import com.lishi.recruitment.bean.param.Condition;
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
     * @param paramCondition FindRecruit
     * @return Wrapper<AllRecruit>
     */
    public Wrapper<AllRecruit> findRecruitByCondition(ParamCondition paramCondition) {
        Condition condition = handleParamCondition(paramCondition);
        AllRecruit allRecruit = new AllRecruit();
        allRecruit.setJobs(recruitMapper.findRecruitByCondition(condition.getConditionWithLimit()));
        allRecruit.setCount(recruitMapper.findRecruitCountByCondition(condition.getCondition()));

        return WrapMapper.okObtain("获取成功", allRecruit);
    }

    /**
     * 根据条件获取公司信息
     *
     * @param paramCondition FindRecruit
     * @return Wrapper<AllCompany>
     */
    public Wrapper<AllCompany> findCompanyByCondition(ParamCondition paramCondition) {
        Condition condition = handleParamCondition(paramCondition);
        AllCompany allCompany = new AllCompany();
        allCompany.setCompanies(recruitMapper.findCompanyByCondition(condition.getConditionWithLimit()));
        allCompany.setCount(recruitMapper.findCompanyCountByCondition(condition.getCondition()));

        return WrapMapper.okObtain("获取成功", allCompany);
    }

    /**
     * 处理参数条件
     *
     * @param paramCondition ParamCondition
     * @return Condition
     */
    private Condition handleParamCondition(ParamCondition paramCondition) {
        List<ParamWhere> wheres = paramCondition.getWheres();
        StringBuilder sb = new StringBuilder();
        String limit = "";
        if (wheres != null && !wheres.isEmpty()) {
            sb.append("where ");
            for (ParamWhere where : wheres) {
                sb.append(where.getKey()).append(" ").append(where.getOpt()).append(" '").
                        append(where.getValue()).append("' ").append(where.getNext()).append(" ");
            }
        }
        if (ValueUtils.valNotEmpty(paramCondition.getOrder()) && ValueUtils.valNotEmpty(paramCondition.getOrderBy())) {
            sb.append("order by `").append(paramCondition.getOrderBy()).append("` ").
                    append(paramCondition.getOrder()).append(" ");
        }
        if (paramCondition.getPageNum() > 0 && paramCondition.getPageSize() > 0) {
            int pageNum = (paramCondition.getPageNum() - 1) * paramCondition.getPageSize();
            limit = "limit " + pageNum + ", " + paramCondition.getPageSize() + " ";
        }
        return new Condition(sb.toString(), sb.toString() + limit);
    }

}
