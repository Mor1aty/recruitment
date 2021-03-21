package com.lishi.recruitment.controller;

import com.lishi.recruitment.annotation.login.aspect.NeedLogin;
import com.lishi.recruitment.annotation.valid.aspect.Param;
import com.lishi.recruitment.annotation.valid.aspect.ParamValidation;
import com.lishi.recruitment.annotation.valid.bean.Method;
import com.lishi.recruitment.bean.UserToken;
import com.lishi.recruitment.bean.back.CandidateInfo;
import com.lishi.recruitment.bean.back.Login;
import com.lishi.recruitment.constant.Constant;
import com.lishi.recruitment.service.UserService;
import com.lishi.recruitment.wrap.WrapParams;
import com.lishi.recruitment.wrap.Wrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiShi
 * date: 2021/1/9 17:07
 * description: 用户相关操作 Controller
 */
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 登录
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("login")
    @ParamValidation({@Param("account"), @Param("password"), @Param(value = "type", method = Method.NUMBER)})
    public Wrapper<Login> login(WrapParams wrapParams) {
        return userService.login(wrapParams.getString("account"),
                wrapParams.getString("password"), wrapParams.getIntValue("type"));
    }

    /**
     * 注销
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("logout")
    @NeedLogin("user")//必须登入才可以调用接口
    @ParamValidation
    public Wrapper<String> logout(WrapParams wrapParams) {
        return userService.logout((UserToken) wrapParams.getTokenValue("user"));//强制转化类型，得到系统存储的用户登入数据
    }

    /**
     * 注册应聘者
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("registerCandidate")
    @ParamValidation({@Param("account"), @Param("password"), @Param("name"), @Param("gender"),
            @Param(value = "phone", method = Method.PHONE), @Param(value = "email", method = Method.EMAIL),
            @Param(value = "birthday")})
    public Wrapper<String> registerCandidate(WrapParams wrapParams) { //前端传来的参数全放WrapParams里
        return userService.registerCandidate(wrapParams.getString("account"), wrapParams.getString("password"),
                wrapParams.getString("name"), wrapParams.getString("gender"), wrapParams.getString("phone"),
                wrapParams.getString("email"), wrapParams.getString("birthday"));
    }

    /**
     * 注册公司
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("registerCompany")
    @ParamValidation({@Param("account"), @Param("password"), @Param("name"), @Param("city"), @Param("code")})
    public Wrapper<String> registerCompany(WrapParams wrapParams) {
        return userService.registerCompany(wrapParams.getString("account"), wrapParams.getString("password"),
                wrapParams.getString("name"), wrapParams.getString("city"), wrapParams.getString("website"),
                wrapParams.getString("code"), wrapParams.getString("desc"));
    }

    /**
     * 获取应聘者信息
     *
     * @param wrapParams WrapParams
     * @return Wrapper<CandidateInfo>
     */
    @PostMapping("findCandidateInfo")
    @NeedLogin(level = Constant.IDENTITY_CANDIDATE)
    @ParamValidation
    public Wrapper<CandidateInfo> findCandidateInfo(WrapParams wrapParams) {
        return userService.findCandidateInfo(((UserToken) wrapParams.getTokenValue("token")).getAccount());
    }//token：保留用户登入状态的信息

    /**
     * 修改密码
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("changePassword")
    @NeedLogin
    @ParamValidation({@Param("oldPassword"), @Param("newPassword"), @Param(value = "type", method = Method.NUMBER)})
    public Wrapper<String> changePassword(WrapParams wrapParams) {//前端传过来的参数全放wrapParams里
        return userService.changePassword(((UserToken) wrapParams.getTokenValue("token")).getAccount(),
                wrapParams.getString("oldPassword"), wrapParams.getString("newPassword"),
                wrapParams.getIntValue("type"));
    }

    /**
     * 应聘者修改个人信息
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("updateCandidate")
    @NeedLogin(level = Constant.IDENTITY_CANDIDATE)//此次请求是应聘者界面用的
    @ParamValidation({@Param("name"), @Param("gender"), @Param(value = "phone", method = Method.PHONE),
            @Param(value = "email", method = Method.EMAIL), @Param(value = "birthday")})
    public Wrapper<String> updateCandidate(WrapParams wrapParams) {
        return userService.updateCandidate(((UserToken) wrapParams.getTokenValue("token")).getAccount(),
                wrapParams.getString("name"), wrapParams.getString("gender"), wrapParams.getString("phone"),
                wrapParams.getString("email"), wrapParams.getString("birthday"), wrapParams.getString("desc"));
    }

    /**
     * 公司修改个人信息
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("updateCompany")
    @NeedLogin(level = Constant.IDENTITY_COMPANY)
    @ParamValidation({@Param("name"), @Param("city"), @Param("code")})
    public Wrapper<String> updateCompany(WrapParams wrapParams) {
        return userService.updateCompany(((UserToken) wrapParams.getTokenValue("token")).getAccount(),
                wrapParams.getString("name"), wrapParams.getString("city"), wrapParams.getString("website"),
                wrapParams.getString("code"), wrapParams.getString("desc"));
    }

    /**
     * 应聘者增加教育经历
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("addEducationExperience")
    @NeedLogin(level = Constant.IDENTITY_CANDIDATE)
    @ParamValidation({@Param("school"), @Param("academic"), @Param("major"), @Param("startDate"), @Param("endDate")})
    public Wrapper<String> addEducationExperience(WrapParams wrapParams) {
        return userService.addEducationExperience(((UserToken) wrapParams.getTokenValue("token")).getAccount(),
                wrapParams.getString("school"), wrapParams.getString("academic"), wrapParams.getString("major"),
                wrapParams.getString("startDate"), wrapParams.getString("endDate"), wrapParams.getString("desc"));
    }

    /**
     * 应聘者删除教育经历
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("delEducationExperience")
    @NeedLogin(level = Constant.IDENTITY_CANDIDATE)
    @ParamValidation({@Param(value = "id", method = Method.NUMBER)})
    public Wrapper<String> delEducationExperience(WrapParams wrapParams) {
        return userService.delEducationExperience(((UserToken) wrapParams.getTokenValue("token")).getAccount(),
                wrapParams.getLongValue("id"));
    }


    /**
     * 应聘者增加期望职位
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("addExpectedPosition")
    @NeedLogin(level = Constant.IDENTITY_CANDIDATE)
    @ParamValidation({@Param("city"), @Param("position"), @Param("salary")})
    public Wrapper<String> addExpectedPosition(WrapParams wrapParams) {
        return userService.addExpectedPosition(((UserToken) wrapParams.getTokenValue("token")).getAccount(),
                wrapParams.getString("city"), wrapParams.getString("position"), wrapParams.getString("salary"));
    }

    /**
     * 应聘者删除期望职位
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("delExpectedPosition")
    @NeedLogin(level = Constant.IDENTITY_CANDIDATE)
    @ParamValidation({@Param(value = "id", method = Method.NUMBER)})
    public Wrapper<String> delExpectedPosition(WrapParams wrapParams) {
        return userService.delExpectedPosition(((UserToken) wrapParams.getTokenValue("token")).getAccount(),
                wrapParams.getLongValue("id"));
    }

    /**
     * 应聘者增加工作经历
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("addWorkExperience")
    @NeedLogin(level = Constant.IDENTITY_CANDIDATE)
    @ParamValidation({@Param("company"), @Param("department"), @Param("position")})
    public Wrapper<String> addWorkExperience(WrapParams wrapParams) {
        return userService.addWorkExperience(((UserToken) wrapParams.getTokenValue("token")).getAccount(),
                wrapParams.getString("company"), wrapParams.getString("department"),
                wrapParams.getString("position"), wrapParams.getString("desc"));
    }

    /**
     * 应聘者删除工作经历
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("delWorkExperience")
    @NeedLogin(level = Constant.IDENTITY_CANDIDATE)
    @ParamValidation({@Param(value = "id", method = Method.NUMBER)})
    public Wrapper<String> delWorkExperience(WrapParams wrapParams) {
        return userService.delWorkExperience(((UserToken) wrapParams.getTokenValue("token")).getAccount(),
                wrapParams.getLongValue("id"));
    }
}
