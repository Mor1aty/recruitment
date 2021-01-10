package com.lishi.recruitment.service;

import com.lishi.recruitment.annotation.login.utils.TokenUtil;
import com.lishi.recruitment.bean.UserToken;
import com.lishi.recruitment.bean.back.Login;
import com.lishi.recruitment.bean.db.Candidate;
import com.lishi.recruitment.bean.db.Company;
import com.lishi.recruitment.constant.Constant;
import com.lishi.recruitment.constant.CustomConstant;
import com.lishi.recruitment.mapper.UserMapper;
import com.lishi.recruitment.wrap.WrapMapper;
import com.lishi.recruitment.wrap.Wrapper;
import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.WrapperMappingInfo;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author LiShi
 * date: 2021/1/9 18:01
 * description: 用户相关操作 Service
 */
@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    //声明（调用）查询数据库的对象

    /**
     * 登录
     *
     * @param account  String
     * @param password String
     * @param type     String
     * @return Wrapper<String>
     */
    public Wrapper<Login> login(String account, String password, int type) {
        switch (type) {
            case Constant
                    .IDENTITY_CANDIDATE: {
                Candidate candidate = userMapper.findCandidateByAccountAndPassword(account, password);
                if (candidate == null) {
                    return WrapMapper.errorExec("帐号密码错误");
                }
                return WrapMapper.okExec("登录成功", loginAfter(account, type, candidate));
            }
            case Constant
                    .IDENTITY_COMPANY: {
                Company company = userMapper.findCompanyByAccountAndPassword(account, password);
                if (company == null) {
                    return WrapMapper.errorExec("帐号密码错误");
                }
                return WrapMapper.okExec("登录成功", loginAfter(account, type, company));
            }
            default:
                return WrapMapper.errorExec("登录类型错误");
        }
    }

    /**
     * 登录之后
     *
     * @param account String
     * @param type    int
     * @param obj     Object
     * @return Login
     */
    private Login loginAfter(String account, int type, Object obj) {
        UserToken userToken = new UserToken();
        userToken.setAccount(account);
        userToken.effectiveTime = -1;
        userToken.level = type;
        String tokenCode = TokenUtil.putTokenStorage(userToken);
        Login login = new Login();
        login.setAccount(account);
        login.setTokenCode(tokenCode);
        login.setInfo(obj);
        return login;
    }

    /**
     * 注册应聘者
     *
     * @param account  String
     * @param password String
     * @param name     String
     * @param gender   String
     * @param phone    String
     * @param email    String
     * @param birthday String
     * @return Wrapper<String>
     */
    public Wrapper<String> registerCandidate(String account, String password, String name, String gender,
                                             String phone, String email, String birthday) {
        try {
            // 1、判断帐号是否存在
            Candidate candidate = userMapper.findCandidateByAccount(account);
            if (candidate != null) {
                return WrapMapper.errorExec("帐号已存在");
            }

            // 2、信息存入数据库
            Candidate newCandidate = new Candidate();
            newCandidate.setAccount(account);
            newCandidate.setName(name);
            newCandidate.setPassword(password);
            newCandidate.setGender(gender);
            newCandidate.setPhone(phone);
            newCandidate.setEmail(email);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CustomConstant.Date.DATE_TYPE_02);
            newCandidate.setBirthday(simpleDateFormat.parse(birthday));

            if (userMapper.insertCandidate(newCandidate) <= 0) {
                return WrapMapper.errorExec("注册应聘者失败");
            }
            return WrapMapper.okExec("注册应聘者成功");
        } catch (ParseException e) {
            return WrapMapper.errorExec("生日格式错误");
        }
    }
}
