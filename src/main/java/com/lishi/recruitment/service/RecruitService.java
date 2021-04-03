package com.lishi.recruitment.service;

import com.lishi.recruitment.bean.back.AllCompany;
import com.lishi.recruitment.bean.back.AllProgress;
import com.lishi.recruitment.bean.back.AllRecruit;
import com.lishi.recruitment.bean.back.BackProgress;
import com.lishi.recruitment.bean.db.Job;
import com.lishi.recruitment.bean.db.JobType;
import com.lishi.recruitment.bean.db.Progress;
import com.lishi.recruitment.bean.param.Condition;
import com.lishi.recruitment.bean.param.ParamCondition;
import com.lishi.recruitment.bean.param.ParamWhere;
import com.lishi.recruitment.constant.Constant;
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
        Condition condition = handleParamCondition(paramCondition); // 把前端传来的参数处理成 SQL 的条件
        AllRecruit allRecruit = new AllRecruit(); // 返回给前端的
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
        List<ParamWhere> wheres = paramCondition.getWheres(); // 前端传入的判断条件
        StringBuilder sb = new StringBuilder();//拼接修改字符串
        if (wheres != null && !wheres.isEmpty()) {
            sb.append("where ");
            for (ParamWhere where : wheres) {
                sb.append(where.getKey()).append(" ").append(where.getOpt()).append(" '").
                        append(where.getValue()).append("' ");
                if (where.getNext() != null) {
                    sb.append(where.getNext()).append(" ");
                }
            }
        }
        // 做排序
        if (ValueUtils.valNotEmpty(paramCondition.getOrder()) && ValueUtils.valNotEmpty(paramCondition.getOrderBy())) {
            sb.append("order by ").append(paramCondition.getOrderBy()).append(" ").
                    append(paramCondition.getOrder()).append(" ");
        }
        // 做分页
        String limit = "";
        if (paramCondition.getPageNum() > 0 && paramCondition.getPageSize() > 0) {
            int pageNum = (paramCondition.getPageNum() - 1) * paramCondition.getPageSize();
            limit = "limit " + pageNum + ", " + paramCondition.getPageSize() + " ";
        }
        return new Condition(sb.toString(), sb.toString() + limit);
    }

    /**
     * 公司发布新的招聘
     *
     * @param name      String
     * @param type      int
     * @param company   String
     * @param desc      String
     * @param minSalary int
     * @param maxSalary int
     * @param city      String
     * @return Wrapper<String>
     */
    public Wrapper<String> addRecruit(String name, int type, String company, String desc, int minSalary, int maxSalary, String city) {
        Job job = new Job();
        job.setName(name);
        job.setType(type);
        job.setCompany(company);
        job.setDesc(desc);
        job.setMinSalary(minSalary);
        job.setMaxSalary(maxSalary);
        job.setCity(city);
        if (recruitMapper.insertJob(job) <= 0) {
            return WrapMapper.errorExec("发布新招聘失败");
        }
        return WrapMapper.okExec("发布新招聘成功");
    }

    /**
     * 公司修改招聘
     *
     * @param id        int
     * @param name      String
     * @param type      int
     * @param desc      String
     * @param minSalary int
     * @param maxSalary int
     * @param city      String
     * @return Wrapper<String>
     */
    public Wrapper<String> editRecruit(int id, String name, int type, String desc, int minSalary, int maxSalary,
                                       String city, String company) {
        Job job = new Job();
        job.setId(id);
        job.setName(name);
        job.setType(type);
        job.setDesc(desc);
        job.setMinSalary(minSalary);
        job.setMaxSalary(maxSalary);
        job.setCity(city);
        job.setCompany(company);
        if (recruitMapper.updateJob(job) <= 0) {
            return WrapMapper.errorExec("更新招聘失败");
        }
        return WrapMapper.okExec("更新招聘成功");
    }

    /**
     * 公司删除招聘
     *
     * @param id      int
     * @param company String
     * @return Wrapper<String>
     */
    public Wrapper<String> delRecruit(int id, String company) {
        if (recruitMapper.deleteJob(id, company) <= 0) {
            return WrapMapper.errorExec("删除招聘失败");
        }
        return WrapMapper.okExec("删除招聘成功");
    }

    /**
     * 公司获取招聘进度
     *
     * @param job int
     * @return Wrapper<AllProgress>
     */
    public Wrapper<AllProgress> findProgress(int job) {
        List<BackProgress> progresses = recruitMapper.findProgressByJob(job);
        AllProgress allProgress = new AllProgress();
        allProgress.setProgresses(progresses);
        allProgress.setCount(progresses.size());
        return WrapMapper.okObtain("获取成功", allProgress);
    }

    /**
     * 公司修改招聘进度
     *
     * @param id     int
     * @param result int
     * @return Wrapper<String>
     */
    public Wrapper<String> editProgress(int id, int result) {
        if (recruitMapper.updateProgress(id, result) <= 0) {
            return WrapMapper.errorExec("修改招聘进度失败");
        }
        return WrapMapper.okExec("修改招聘进度成功");
    }

    /**
     * 个人选择工作
     *
     * @param job       int
     * @param candidate String
     * @return Wrapper<String>
     */
    public Wrapper<String> chooseJob(int job, String candidate) {
        Progress progress = new Progress();
        progress.setJob(job);
        progress.setCandidate(candidate);
        progress.setProgress(Constant.PROGRESS_WAIT);
        if (recruitMapper.insertProgress(progress) <= 0) {
            return WrapMapper.errorExec("选择工作失败");
        }
        return WrapMapper.okExec("选择工作成功");
    }

    /**
     * 个人查询我的应聘
     *
     * @param candidate String
     * @return Wrapper<AllProgress>
     */
    public Wrapper<AllProgress> findMyProgress(String candidate) {
        List<BackProgress> progresses = recruitMapper.findProgressByCandidate(candidate);
        AllProgress allProgress = new AllProgress();
        allProgress.setProgresses(progresses);
        allProgress.setCount(progresses.size());
        return WrapMapper.okObtain("获取成功", allProgress);
    }

    /**
     * 获取所有职位类型
     *
     * @return Wrapper<List < JobType>>
     */
    public Wrapper<List<JobType>> findAllJobType() {
        return WrapMapper.okObtain("获取成功", recruitMapper.findAllJobType());
    }

    /**
     * 个人查询职位的个人应聘进度
     *
     * @param account String
     * @param job     int
     * @return Wrapper
     */
    public Wrapper<BackProgress> findJobProgress(String account, int job) {
        return WrapMapper.okObtain("获取成功", recruitMapper.findJobProgress(account, job));
    }
}
