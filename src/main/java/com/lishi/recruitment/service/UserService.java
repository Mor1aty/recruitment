package com.lishi.recruitment.service;

import com.lishi.recruitment.annotation.login.utils.TokenUtil;
import com.lishi.recruitment.bean.UserToken;
import com.lishi.recruitment.bean.back.CandidateInfo;
import com.lishi.recruitment.bean.back.Login;
import com.lishi.recruitment.bean.db.*;
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
import java.util.List;

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
        login.setType(type);
        return login;
    }

    /**
     * 注销
     *
     * @param token UserToken
     * @return Wrapper<String>
     */
    public Wrapper<String> logout(UserToken token) {
        TokenUtil.removeToken(token.code);
        return WrapMapper.okExec("注销成功");
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

    /**
     * 注册公司
     *
     * @param account  String
     * @param password String
     * @param name     String
     * @param city     String
     * @param website  String
     * @param code     String
     * @param desc     String
     * @return Wrapper<String>
     */
    public Wrapper<String> registerCompany(String account, String password, String name, String city, String website,
                                           String code, String desc) {
        // 1、判断帐号是否存在
        Company company = userMapper.findCompanyByAccount(account);
        if (company != null) {
            return WrapMapper.errorExec("帐号已存在");
        }

        // 2、信息存入数据库
        Company newCompany = new Company();
        newCompany.setAccount(account);
        newCompany.setPassword(password);
        newCompany.setName(name);
        newCompany.setCity(city);
        newCompany.setWebsite(website);
        newCompany.setCode(code);
        newCompany.setDesc(desc);

        if (userMapper.insertCompany(newCompany) <= 0) {
            return WrapMapper.errorExec("注册公司失败");
        }
        return WrapMapper.okExec("注册公司成功");
    }

    /**
     * 修改密码
     *
     * @param account     String
     * @param oldPassword String
     * @param newPassword String
     * @param type        int
     * @return Wrapper<String>
     */
    public Wrapper<String> changePassword(String account, String oldPassword, String newPassword, int type) {
        switch (type) {
            case Constant.IDENTITY_CANDIDATE:
                Candidate candidate = userMapper.findCandidateByAccountAndPassword(account, oldPassword);
                if (candidate == null) {
                    return WrapMapper.errorExec("应聘者旧密码错误");
                }
                if (userMapper.updateCandidatePassword(account, newPassword) <= 0) {
                    return WrapMapper.errorExec("应聘者修改密码失败");
                }
                return WrapMapper.okExec("应聘者修改密码成功");
            case Constant.IDENTITY_COMPANY:
                Company company = userMapper.findCompanyByAccountAndPassword(account, oldPassword);
                if (company == null) {
                    return WrapMapper.errorExec("公司旧密码错误");
                }
                if (userMapper.updateCompanyPassword(account, newPassword) <= 0) {
                    return WrapMapper.errorExec("公司修改密码失败");
                }
                return WrapMapper.okExec("公司修改密码成功");
            default:
                return WrapMapper.errorExec("帐号类型错误");
        }
    }

    /**
     * 获取应聘者信息
     *
     * @param account String
     * @return Wrapper<CandidateInfo>
     */
    public Wrapper<CandidateInfo> findCandidateInfo(String account) {

        Candidate candidate = userMapper.findCandidateByAccount(account);
        if (candidate == null) {
            return WrapMapper.errorObtain("应聘者信息获取失败");
        }
        CandidateInfo candidateInfo = new CandidateInfo();
        candidateInfo.setAccount(account);
        candidateInfo.setName(candidate.getName());
        candidateInfo.setGender(candidate.getGender());
        candidateInfo.setPhone(candidate.getPhone());
        candidateInfo.setEmail(candidate.getEmail());
        candidateInfo.setBirthday(candidate.getBirthday());
        candidateInfo.setDesc(candidate.getDesc());
        candidateInfo.setEducationExperienceList(userMapper.findEducationExperienceByCandidate(account));
        candidateInfo.setExpectedPositionList(userMapper.findExpectedPositionByCandidate(account));
        candidateInfo.setWorkExperienceList(userMapper.findWorkExperienceByCandidate(account));
        return WrapMapper.okObtain("应聘者信息获取成功", candidateInfo);
    }

    /**
     * 应聘者修改个人信息
     *
     * @param account  String
     * @param name     String
     * @param gender   String
     * @param phone    String
     * @param email    String
     * @param birthday String
     * @param desc     String
     * @return Wrapper<String>
     */
    public Wrapper<String> updateCandidate(String account, String name, String gender, String phone, String email,
                                           String birthday, String desc) {
        try {
            Candidate candidate = new Candidate();
            candidate.setAccount(account);
            candidate.setName(name);
            candidate.setGender(gender);
            candidate.setPhone(phone);
            candidate.setEmail(email);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CustomConstant.Date.DATE_TYPE_02);
            candidate.setBirthday(simpleDateFormat.parse(birthday));
            candidate.setDesc(desc);
            if (userMapper.updateCandidate(candidate) <= 0) {
                return WrapMapper.errorExec("应聘者修改个人信息失败");
            }
            return WrapMapper.okExec("应聘者修改个人信息成功");
        } catch (ParseException e) {
            return WrapMapper.errorExec("生日格式错误");
        }
    }

    /**
     * 公司修改个人信息
     *
     * @param account String
     * @param name    String
     * @param city    String
     * @param website String
     * @param code    String
     * @param desc    String
     * @return Wrapper<String>
     */
    public Wrapper<String> updateCompany(String account, String name, String city, String website, String code, String desc) {
        Company company = new Company();
        company.setAccount(account);
        company.setName(name);
        company.setCity(city);
        company.setWebsite(website);
        company.setCode(code);
        company.setDesc(desc);
        if (userMapper.updateCompany(company) <= 0) {
            return WrapMapper.errorExec("公司修改个人信息失败");
        }
        return WrapMapper.okExec("公司修改个人信息成功");
    }

    /**
     * 应聘者增加教育经历
     *
     * @param candidate String
     * @param school    String
     * @param academic  String
     * @param major     String
     * @param startDate String
     * @param endDate   String
     * @param desc      String
     * @return Wrapper<String>
     */
    public Wrapper<String> addEducationExperience(String candidate, String school, String academic, String major,
                                                  String startDate, String endDate, String desc) {
        try {
            EducationExperience educationExperience = new EducationExperience();
            educationExperience.setCandidate(candidate);
            educationExperience.setSchool(school);
            educationExperience.setAcademic(academic);
            educationExperience.setMajor(major);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CustomConstant.Date.DATE_TYPE_02);
            educationExperience.setStartDate(simpleDateFormat.parse(startDate));
            educationExperience.setEndDate(simpleDateFormat.parse(endDate));
            educationExperience.setDesc(desc);
            if (userMapper.addEducationExperience(educationExperience) <= 0) {
                return WrapMapper.errorExec("增加教育经历失败");
            }
            return WrapMapper.okExec("增加教育经历成功");
        } catch (ParseException e) {
            return WrapMapper.errorExec("生日格式错误");
        }
    }

    /**
     * 应聘者删除教育经历
     *
     * @param candidate String
     * @param id        long
     * @return Wrapper<String>
     */
    public Wrapper<String> delEducationExperience(String candidate, long id) {
        if (userMapper.delEducationExperience(candidate, id) <= 0) {
            return WrapMapper.errorExec("删除教育经历失败");
        }
        return WrapMapper.okExec("删除教育经历成功");
    }

    /**
     * 应聘者增加期望职位
     *
     * @param candidate String
     * @param city      String
     * @param position  String
     * @param salary    String
     * @return Wrapper<String>
     */
    public Wrapper<String> addExpectedPosition(String candidate, String city, String position, String salary) {

        ExpectedPosition expectedPosition = new ExpectedPosition();
        expectedPosition.setCandidate(candidate);
        expectedPosition.setCity(city);
        expectedPosition.setPosition(position);
        expectedPosition.setSalary(salary);
        if (userMapper.addExpectedPosition(expectedPosition) <= 0) {
            return WrapMapper.errorExec("增加期望职位失败");
        }
        return WrapMapper.okExec("增加期望职位成功");

    }

    /**
     * 应聘者删除期望职位
     *
     * @param candidate String
     * @param id        long
     * @return Wrapper<String>
     */
    public Wrapper<String> delExpectedPosition(String candidate, long id) {
        if (userMapper.delExpectedPosition(candidate, id) <= 0) {
            return WrapMapper.errorExec("删除教育经历失败");
        }
        return WrapMapper.okExec("删除教育经历成功");
    }

    /**
     * 应聘者增加工作经历
     *
     * @param candidate  String
     * @param company    String
     * @param department String
     * @param position   String
     * @param desc       String
     * @return Wrapper<String>
     */
    public Wrapper<String> addWorkExperience(String candidate, String company, String department, String position, String desc) {

        WorkExperience workExperience = new WorkExperience();
        workExperience.setCandidate(candidate);
        workExperience.setCompany(company);
        workExperience.setDepartment(department);
        workExperience.setPosition(position);
        workExperience.setDesc(desc);
        if (userMapper.addWorkExperience(workExperience) <= 0) {
            return WrapMapper.errorExec("增加工作经历失败");
        }
        return WrapMapper.okExec("增加工作经历成功");

    }

    /**
     * 应聘者删除工作经历
     *
     * @param candidate String
     * @param id        long
     * @return Wrapper<String>
     */
    public Wrapper<String> delWorkExperience(String candidate, long id) {
        if (userMapper.delWorkExperience(candidate, id) <= 0) {
            return WrapMapper.errorExec("删除工作经历失败");
        }
        return WrapMapper.okExec("删除工作经历成功");
    }
}
