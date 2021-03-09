package com.lishi.recruitment.controller;

import com.lishi.recruitment.annotation.login.aspect.NeedLogin;
import com.lishi.recruitment.annotation.valid.aspect.Param;
import com.lishi.recruitment.annotation.valid.aspect.ParamValidation;
import com.lishi.recruitment.annotation.valid.bean.Method;
import com.lishi.recruitment.bean.UserToken;
import com.lishi.recruitment.bean.back.AllCompany;
import com.lishi.recruitment.bean.back.AllProgress;
import com.lishi.recruitment.bean.back.AllRecruit;
import com.lishi.recruitment.bean.back.CandidateInfo;
import com.lishi.recruitment.bean.db.JobType;
import com.lishi.recruitment.bean.param.ParamCondition;
import com.lishi.recruitment.constant.Constant;
import com.lishi.recruitment.service.RecruitService;
import com.lishi.recruitment.service.UserService;
import com.lishi.recruitment.wrap.WrapParams;
import com.lishi.recruitment.wrap.Wrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiShi
 * date: 2021/2/19 10:05
 * description: 招聘 Controller
 */
@RestController
@RequestMapping("recruit")
@AllArgsConstructor
public class RecruitController {

    private final RecruitService recruitService;
    private final UserService userService;

    /**
     * 根据条件获取招聘信息
     *
     * @param wrapParams WrapParams
     * @return Wrapper<AllRecruit>
     */
    @PostMapping("findRecruit")
    @ParamValidation
    public Wrapper<AllRecruit> findRecruitByCondition(WrapParams wrapParams) {
        return recruitService.findRecruitByCondition(wrapParams.getObject(ParamCondition.class));
    }

    /**
     * 根据条件获取公司信息
     *
     * @param wrapParams WrapParams
     * @return Wrapper<AllCompany>
     */
    @PostMapping("findCompany")
    @ParamValidation
    public Wrapper<AllCompany> findCompanyByCondition(WrapParams wrapParams) {
        return recruitService.findCompanyByCondition(wrapParams.getObject(ParamCondition.class));
    }

    /**
     * 公司发布新的招聘
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("addRecruit")
    @NeedLogin(level = Constant.IDENTITY_COMPANY)
    @ParamValidation({@Param("name"), @Param(value = "type", method = Method.NUMBER), @Param("desc"), @Param("city"),
            @Param(value = "min_salary", method = Method.NUMBER), @Param(value = "max_salary", method = Method.NUMBER)})
    public Wrapper<String> addRecruit(WrapParams wrapParams) {
        return recruitService.addRecruit(wrapParams.getString("name"), wrapParams.getIntValue("type"),
                ((UserToken) wrapParams.getTokenValue("user")).getAccount(), wrapParams.getString("desc"),
                wrapParams.getIntValue("minSalary"), wrapParams.getIntValue("maxSalary"),
                wrapParams.getString("city"));
    }

    /**
     * 公司修改招聘
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("editRecruit")
    @NeedLogin(level = Constant.IDENTITY_COMPANY)
    @ParamValidation({@Param(value = "id", method = Method.NUMBER), @Param("name"), @Param(value = "type", method = Method.NUMBER),
            @Param("desc"), @Param("city"), @Param(value = "min_salary", method = Method.NUMBER),
            @Param(value = "max_salary", method = Method.NUMBER)})
    public Wrapper<String> editRecruit(WrapParams wrapParams) {
        return recruitService.editRecruit(wrapParams.getIntValue("id"), wrapParams.getString("name"),
                wrapParams.getIntValue("type"), wrapParams.getString("desc"), wrapParams.getIntValue("minSalary"),
                wrapParams.getIntValue("maxSalary"), wrapParams.getString("city"),
                ((UserToken) wrapParams.getTokenValue("user")).getAccount());
    }

    /**
     * 公司删除招聘
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("delRecruit")
    @NeedLogin(level = Constant.IDENTITY_COMPANY)
    @ParamValidation({@Param(value = "id", method = Method.NUMBER)})
    public Wrapper<String> delRecruit(WrapParams wrapParams) {
        return recruitService.delRecruit(wrapParams.getIntValue("id"),
                ((UserToken) wrapParams.getTokenValue("user")).getAccount());
    }

    /**
     * 公司查询招聘进度
     *
     * @param wrapParams WrapParams
     * @return Wrapper<AllProgress>
     */
    @PostMapping("findProgress")
    @NeedLogin(level = Constant.IDENTITY_COMPANY)
    @ParamValidation({@Param(value = "job", method = Method.NUMBER)})
    public Wrapper<AllProgress> findProgress(WrapParams wrapParams) {
        return recruitService.findProgress(wrapParams.getIntValue("job"));
    }

    /**
     * 公司修改招聘进度
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("editProgress")
    @NeedLogin(level = Constant.IDENTITY_COMPANY)
    @ParamValidation({@Param(value = "id", method = Method.NUMBER), @Param(value = "result", method = Method.NUMBER)})
    public Wrapper<String> editProgress(WrapParams wrapParams) {
        return recruitService.editProgress(wrapParams.getIntValue("id"), wrapParams.getIntValue("result"));
    }

    /**
     * 公司查询应聘者信息
     *
     * @param wrapParams WrapParams
     * @return Wrapper<CandidateInfo>
     */
    @PostMapping("findCandidate")
    @NeedLogin(level = Constant.IDENTITY_COMPANY)
    @ParamValidation({@Param("candidate")})
    public Wrapper<CandidateInfo> findCandidate(WrapParams wrapParams) {
        return userService.findCandidateInfo(wrapParams.getString("candidate"));
    }

    /**
     * 个人选择工作
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("chooseJob")
    @NeedLogin(level = Constant.IDENTITY_CANDIDATE)
    @ParamValidation({@Param(value = "job", method = Method.NUMBER)})
    public Wrapper<String> chooseJob(WrapParams wrapParams) {
        return recruitService.chooseJob(wrapParams.getIntValue("job"),
                ((UserToken) wrapParams.getTokenValue("user")).getAccount());
    }

    /**
     * 个人查询我的应聘
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("findMyProgress")
    @NeedLogin(level = Constant.IDENTITY_CANDIDATE)
    @ParamValidation
    public Wrapper<AllProgress> findMyProgress(WrapParams wrapParams) {
        return recruitService.findMyProgress(((UserToken) wrapParams.getTokenValue("user")).getAccount());
    }

    /**
     * 获取所有职位类型
     *
     * @return Wrapper<List < JobType>>
     */
    @PostMapping("findAllJobType")
    public Wrapper<List<JobType>> findAllJobType() {
        return recruitService.findAllJobType();
    }
}
