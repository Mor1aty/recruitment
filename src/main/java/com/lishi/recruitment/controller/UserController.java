package com.lishi.recruitment.controller;

import com.lishi.recruitment.annotation.valid.aspect.Param;
import com.lishi.recruitment.annotation.valid.aspect.ParamValidation;
import com.lishi.recruitment.annotation.valid.bean.Method;
import com.lishi.recruitment.bean.back.Login;
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
     * 注册应聘者
     *
     * @param wrapParams WrapParams
     * @return Wrapper<String>
     */
    @PostMapping("registerCandidate")
    @ParamValidation({@Param("account"), @Param("password"), @Param("name"), @Param("gender"),
            @Param(value = "phone", method = Method.PHONE), @Param(value = "email", method = Method.EMAIL),
            @Param(value = "birthday")})
    public Wrapper<String> registerCandidate(WrapParams wrapParams) {
        return userService.registerCandidate(wrapParams.getString("account"), wrapParams.getString("password"),
                wrapParams.getString("name"), wrapParams.getString("gender"), wrapParams.getString("phone"),
                wrapParams.getString("email"), wrapParams.getString("birthday"));
    }

    // 注册公司


}
