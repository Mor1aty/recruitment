package com.lishi.recruitment.controller;

import com.lishi.recruitment.annotation.login.aspect.NeedLogin;
import com.lishi.recruitment.annotation.valid.aspect.Param;
import com.lishi.recruitment.annotation.valid.aspect.ParamValidation;
import com.lishi.recruitment.annotation.valid.bean.Method;
import com.lishi.recruitment.bean.UserToken;
import com.lishi.recruitment.bean.back.*;
import com.lishi.recruitment.constant.Constant;
import com.lishi.recruitment.service.AdminService;
import com.lishi.recruitment.wrap.WrapParams;
import com.lishi.recruitment.wrap.Wrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiShi
 * date: 2021/4/3 17:46
 * description: 管理员 Controller
 */
@RestController
@RequestMapping("admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * 管理员登录
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("login")
    @ParamValidation({@Param("account"), @Param("password")})
    public Wrapper<Login> login(WrapParams wrapParams) {
        return adminService.login(wrapParams.getString("account"), wrapParams.getString("password"));
    }

    /**
     * 注销
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("logout")
    @NeedLogin(level = Constant.IDENTITY_ADMIN)//必须登入才可以调用接口
    @ParamValidation
    public Wrapper<String> logout(WrapParams wrapParams) {
        return adminService.logout((UserToken) wrapParams.getTokenValue("token"));//强制转化类型，得到系统存储的用户登入数据
    }

    /**
     * 获取所有应聘者
     *
     * @param wrapParams WrapParams
     * @return Wrapper<AllCandidate>
     */
    @PostMapping("finaAllCandidate")
    @NeedLogin(level = Constant.IDENTITY_ADMIN)//必须登入才可以调用接口
    @ParamValidation({@Param(value = "pageSize", method = Method.NUMBER),
            @Param(value = "pageNum", method = Method.NUMBER)})
    public Wrapper<AllCandidate> finaAllCandidate(WrapParams wrapParams) {
        return adminService.finaAllCandidate(wrapParams.getIntValue("pageNum"),
                wrapParams.getIntValue("pageSize"), wrapParams.getString("query"));//强制转化类型，得到系统存储的用户登入数据
    }

    /**
     * 删除应聘者
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("delCandidate")
    @NeedLogin(level = Constant.IDENTITY_ADMIN)//必须登入才可以调用接口
    @ParamValidation({@Param("candidate")})
    public Wrapper<String> delCandidate(WrapParams wrapParams) {
        return adminService.delCandidate(wrapParams.getString("candidate"));//强制转化类型，得到系统存储的用户登入数据
    }

    /**
     * 获取所有公司
     *
     * @param wrapParams WrapParams
     * @return Wrapper<AllCompany>
     */
    @PostMapping("finaAllCompany")
    @NeedLogin(level = Constant.IDENTITY_ADMIN)//必须登入才可以调用接口
    @ParamValidation({@Param(value = "pageSize", method = Method.NUMBER),
            @Param(value = "pageNum", method = Method.NUMBER)})
    public Wrapper<AllCompany> finaAllCompany(WrapParams wrapParams) {
        return adminService.finaAllCompany(wrapParams.getIntValue("pageNum"),
                wrapParams.getIntValue("pageSize"), wrapParams.getString("query"));//强制转化类型，得到系统存储的用户登入数据
    }

    /**
     * 删除公司
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("delCompany")
    @NeedLogin(level = Constant.IDENTITY_ADMIN)//必须登入才可以调用接口
    @ParamValidation({@Param("company")})
    public Wrapper<String> delCompany(WrapParams wrapParams) {
        return adminService.delCompany(wrapParams.getString("company"));//强制转化类型，得到系统存储的用户登入数据
    }

    /**
     * 获取所有招聘
     *
     * @param wrapParams WrapParams
     * @return Wrapper<AllRecruit>
     */
    @PostMapping("finaAllRecruit")
    @NeedLogin(level = Constant.IDENTITY_ADMIN)//必须登入才可以调用接口
    @ParamValidation({@Param(value = "pageSize", method = Method.NUMBER),
            @Param(value = "pageNum", method = Method.NUMBER)})
    public Wrapper<AllRecruit> finaAllRecruit(WrapParams wrapParams) {
        return adminService.finaAllRecruit(wrapParams.getIntValue("pageNum"),
                wrapParams.getIntValue("pageSize"), wrapParams.getString("query"));//强制转化类型，得到系统存储的用户登入数据
    }

    /**
     * 删除招聘
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("delRecruit")
    @NeedLogin(level = Constant.IDENTITY_ADMIN)//必须登入才可以调用接口
    @ParamValidation({@Param(value = "job", method = Method.NUMBER)})
    public Wrapper<String> delRecruit(WrapParams wrapParams) {
        return adminService.delRecruit(wrapParams.getIntValue("job"));//强制转化类型，得到系统存储的用户登入数据
    }

    /**
     * 获取所有职位类型
     *
     * @param wrapParams WrapParams
     * @return Wrapper<AllRecruit>
     */
    @PostMapping("finaAllJobType")
    @NeedLogin(level = Constant.IDENTITY_ADMIN)//必须登入才可以调用接口
    @ParamValidation({@Param(value = "pageSize", method = Method.NUMBER),
            @Param(value = "pageNum", method = Method.NUMBER)})
    public Wrapper<AllJobType> finaAllJobType(WrapParams wrapParams) {
        return adminService.finaAllJobType(wrapParams.getIntValue("pageNum"),
                wrapParams.getIntValue("pageSize"), wrapParams.getString("query"));//强制转化类型，得到系统存储的用户登入数据
    }

    /**
     * 新增职位类型
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("addJobType")
    @NeedLogin(level = Constant.IDENTITY_ADMIN)//必须登入才可以调用接口
    @ParamValidation({@Param("name")})
    public Wrapper<String> addJobType(WrapParams wrapParams) {
        return adminService.addJobType(wrapParams.getString("name"));//强制转化类型，得到系统存储的用户登入数据
    }

    /**
     * 删除职位类型
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("delJobType")
    @NeedLogin(level = Constant.IDENTITY_ADMIN)//必须登入才可以调用接口
    @ParamValidation({@Param(value = "id", method = Method.NUMBER)})
    public Wrapper<String> delJobType(WrapParams wrapParams) {
        return adminService.delJobType(wrapParams.getIntValue("id"));//强制转化类型，得到系统存储的用户登入数据
    }
}
