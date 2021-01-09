package com.lishi.recruitment.controller;

import com.lishi.recruitment.annotation.valid.aspect.Param;
import com.lishi.recruitment.annotation.valid.aspect.ParamValidation;
import com.lishi.recruitment.annotation.valid.bean.Method;
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
    public Wrapper<Object> login(WrapParams wrapParams) {
        return userService.login(wrapParams.getString("account"),
                wrapParams.getString("password"), wrapParams.getIntValue("type"));
    }
}
