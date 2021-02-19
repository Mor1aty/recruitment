package com.lishi.recruitment.controller;

import com.lishi.recruitment.annotation.login.aspect.NeedLogin;
import com.lishi.recruitment.annotation.valid.aspect.ParamValidation;
import com.lishi.recruitment.bean.back.AllCompany;
import com.lishi.recruitment.bean.back.AllRecruit;
import com.lishi.recruitment.bean.param.ParamCondition;
import com.lishi.recruitment.constant.Constant;
import com.lishi.recruitment.service.RecruitService;
import com.lishi.recruitment.wrap.WrapParams;
import com.lishi.recruitment.wrap.Wrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // 公司发布新的招聘
    @PostMapping("addRecruit")
    @ParamValidation
    @NeedLogin(level = Constant.IDENTITY_COMPANY)
    public Wrapper<String> addRecruit(WrapParams wrapParams) {
        return null;
    }
}
