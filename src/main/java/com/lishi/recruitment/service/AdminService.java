package com.lishi.recruitment.service;

import com.lishi.recruitment.annotation.login.utils.TokenUtil;
import com.lishi.recruitment.bean.UserToken;
import com.lishi.recruitment.bean.back.*;
import com.lishi.recruitment.bean.db.Admin;
import com.lishi.recruitment.constant.Constant;
import com.lishi.recruitment.mapper.AdminMapper;
import com.lishi.recruitment.utils.ValueUtils;
import com.lishi.recruitment.wrap.WrapMapper;
import com.lishi.recruitment.wrap.Wrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author LiShi
 * date: 2021/4/3 17:48
 * description: 管理员 Service
 */
@Service
@AllArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;


    /**
     * 管理员登录
     *
     * @param account  String
     * @param password String
     * @return Wrapper<String>
     */
    public Wrapper<Login> login(String account, String password) {
        Admin admin = adminMapper.findAdminByAccountAndPassword(account, password);
        if (admin == null) {
            return WrapMapper.errorExec("帐号密码错误");
        }
        UserToken userToken = new UserToken();
        userToken.setAccount(account);
        userToken.effectiveTime = -1;//永久存储
        userToken.level = Constant.IDENTITY_ADMIN;
        String tokenCode = TokenUtil.putTokenStorage(userToken);
        Login login = new Login();
        login.setAccount(account);
        login.setTokenCode(tokenCode);
        login.setType(Constant.IDENTITY_ADMIN);
        return WrapMapper.okExec("登录成功", login);
    }

    /**
     * 注销
     *
     * @param token UserToken
     * @return Wrapper<String>
     */
    public Wrapper<String> logout(UserToken token) {
        if (token != null) {
            TokenUtil.removeToken(token.code);
        }
        return WrapMapper.okExec("注销成功");
    }

    /**
     * 获取所有应聘者
     *
     * @return Wrapper<AllCandidate>
     */
    public Wrapper<AllCandidate> finaAllCandidate(int pageNum, int pageSize, String query) {
        AllCandidate allCandidate = new AllCandidate();
        pageNum = (pageNum - 1) * pageSize;
        if (ValueUtils.valEmpty(query)) {
            allCandidate.setCandidates(adminMapper.findAllCandidate(pageNum, pageSize));
            allCandidate.setCount(adminMapper.findAllCandidateCount());
        } else {
            query = "%" + query + "%";
            allCandidate.setCandidates(adminMapper.findAllCandidateWithQuery(query, pageNum, pageSize));
            allCandidate.setCount(adminMapper.findAllCandidateCountWithQuery(query));
        }
        return WrapMapper.okObtain("获取成功", allCandidate);
    }

    /**
     * 删除应聘者
     *
     * @param candidate String
     * @return Wrapper<String>
     */
    public Wrapper<String> delCandidate(String candidate) {
        if (adminMapper.delCandidate(candidate) <= 0) {
            return WrapMapper.errorExec("删除失败");
        }
        return WrapMapper.okExec("删除成功");
    }

    /**
     * 获取所有公司
     *
     * @return Wrapper<AllCandidate>
     */
    public Wrapper<AllCompany> finaAllCompany(int pageNum, int pageSize, String query) {
        AllCompany allCompany = new AllCompany();
        pageNum = (pageNum - 1) * pageSize;
        if (ValueUtils.valEmpty(query)) {
            allCompany.setCompanies(adminMapper.findAllCompany(pageNum, pageSize));
            allCompany.setCount(adminMapper.findAllCompanyCount());
        } else {
            query = "%" + query + "%";
            allCompany.setCompanies(adminMapper.findAllCompanyWithQuery(query, pageNum, pageSize));
            allCompany.setCount(adminMapper.findAllCompanyCountWithQuery(query));
        }
        return WrapMapper.okObtain("获取成功", allCompany);
    }

    /**
     * 删除公司
     *
     * @param company String
     * @return Wrapper<String>
     */
    public Wrapper<String> delCompany(String company) {
        if (adminMapper.delCompany(company) <= 0) {
            return WrapMapper.errorExec("删除失败");
        }
        return WrapMapper.okExec("删除成功");
    }

    /**
     * 获取所有招聘
     *
     * @return Wrapper<AllRecruit>
     */
    public Wrapper<AllRecruit> finaAllRecruit(int pageNum, int pageSize, String query) {
        AllRecruit allRecruit = new AllRecruit();
        pageNum = (pageNum - 1) * pageSize;
        if (ValueUtils.valEmpty(query)) {
            allRecruit.setJobs(adminMapper.findAllRecruit(pageNum, pageSize));
            allRecruit.setCount(adminMapper.findAllRecruitCount());
        } else {
            query = "%" + query + "%";
            allRecruit.setJobs(adminMapper.findAllRecruitWithQuery(query, pageNum, pageSize));
            allRecruit.setCount(adminMapper.findAllRecruitCountWithQuery(query));
        }
        return WrapMapper.okObtain("获取成功", allRecruit);
    }

    /**
     * 删除招聘
     *
     * @param job int
     * @return Wrapper<String>
     */
    public Wrapper<String> delRecruit(int job) {
        if (adminMapper.delRecruit(job) <= 0) {
            return WrapMapper.errorExec("删除失败");
        }
        return WrapMapper.okExec("删除成功");
    }

    /**
     * 获取所有职位类型
     *
     * @return Wrapper<AllJobType>
     */
    public Wrapper<AllJobType> finaAllJobType(int pageNum, int pageSize, String query) {
        AllJobType allJobType = new AllJobType();
        pageNum = (pageNum - 1) * pageSize;
        if (ValueUtils.valEmpty(query)) {
            allJobType.setJobTypes(adminMapper.findAllJobType(pageNum, pageSize));
            allJobType.setCount(adminMapper.findAllJobTypeCount());
        } else {
            query = "%" + query + "%";
            allJobType.setJobTypes(adminMapper.findAllJobTypeWithQuery(query, pageNum, pageSize));
            allJobType.setCount(adminMapper.findAllJobTypeCountWithQuery(query));
        }
        return WrapMapper.okObtain("获取成功", allJobType);
    }

    /**
     * 新增职位类型
     *
     * @param name String
     * @return Wrapper<String>
     */
    public Wrapper<String> addJobType(String name) {
        if (adminMapper.insertJobType(name) <= 0) {
            return WrapMapper.errorExec("新增失败");
        }
        return WrapMapper.okExec("新增成功");
    }

    /**
     * 删除职位类型
     *
     * @param id int
     * @return Wrapper<String>
     */
    public Wrapper<String> delJobType(int id) {
        if (adminMapper.delJobType(id) <= 0) {
            return WrapMapper.errorExec("删除失败");
        }
        return WrapMapper.okExec("删除成功");
    }
}
