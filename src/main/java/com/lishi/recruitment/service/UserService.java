package com.lishi.recruitment.service;

import com.lishi.recruitment.bean.db.Candidate;
import com.lishi.recruitment.bean.db.Company;
import com.lishi.recruitment.constant.Constant;
import com.lishi.recruitment.mapper.UserMapper;
import com.lishi.recruitment.wrap.WrapMapper;
import com.lishi.recruitment.wrap.Wrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author LiShi
 * date: 2021/1/9 18:01
 * description: 用户相关操作 Service
 */
@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    /**
     * 登录
     *
     * @param account  String
     * @param password String
     * @param type     String
     * @return Wrapper<String>
     */
    public Wrapper<Object> login(String account, String password, int type) {
        switch (type) {
            case Constant
                    .IDENTITY_CANDIDATE: {
                Candidate candidate = userMapper.findCandidateByAccountAndPassword(account, password);
                if (candidate == null) {
                    return WrapMapper.errorExec("帐号密码错误");
                }
                return WrapMapper.okExec("登录成功", candidate);
            }
            case Constant
                    .IDENTITY_COMPANY: {
                Company company = userMapper.findCompanyByAccountAndPassword(account, password);
                if (company == null) {
                    return WrapMapper.errorExec("帐号密码错误");
                }
                return WrapMapper.okExec("登录成功", company);
            }
            default:
                return WrapMapper.errorExec("登录类型错误");
        }

    }
}
