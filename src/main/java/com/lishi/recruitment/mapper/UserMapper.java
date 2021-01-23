package com.lishi.recruitment.mapper;

import com.lishi.recruitment.bean.db.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author LiShi
 * date: 2021/1/9 18:24
 * description: 用户相关操作 Mapper
 */
public interface UserMapper {

    /**
     * 根据用户名和密码查询应聘者
     *
     * @param account  String
     * @param password String
     * @return Candidate
     */
    @Select("SELECT * FROM candidate WHERE account = #{account} AND password = #{password}")
    Candidate findCandidateByAccountAndPassword(@Param("account") String account, @Param("password") String password);
    //把数据库查询出来的语句放到变量里

    /**
     * 根据用户名和密码查询公司
     *
     * @param account  String
     * @param password String
     * @return Company
     */
    @Select("SELECT * FROM company WHERE account = #{account} AND password = #{password}")
    Company findCompanyByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    /**
     * 根据用户名查询应聘者
     *
     * @param account String
     * @return Candidate
     */
    @Select("SELECT * FROM candidate WHERE account = #{account}")
    Candidate findCandidateByAccount(@Param("account") String account);

    /**
     * 根据用户名查询公司
     *
     * @param account String
     * @return Company
     */
    @Select("SELECT * FROM company WHERE account = #{account}")
    Company findCompanyByAccount(@Param("account") String account);

    /**
     * 插入应聘者
     *
     * @param candidate Candidate
     * @return int
     */
    @Insert("INSERT INTO candidate(account, password, name, gender, phone, email, birthday) " +
            "VALUES(#{account}, #{password}, #{name}, #{gender}, #{phone}, #{email}, #{birthday})")
    int insertCandidate(Candidate candidate);


    /**
     * 插入公司
     *
     * @param company Company
     * @return int
     */
    @Insert("INSERT INTO company(`account`, `password`, `name`, `city`, `website`, `code`, `desc`) " +
            "VALUES(#{account}, #{password}, #{name}, #{city}, #{website}, #{code}, #{desc})")
    int insertCompany(Company company);

    /**
     * 修改应聘者密码
     *
     * @param account  String
     * @param password String
     * @return int
     */
    @Update("UPDATE candidate SET password = #{password} WHERE account = #{account}")
    int updateCandidatePassword(@Param("account") String account, @Param("password") String password);

    /**
     * 修改公司密码
     *
     * @param account  String
     * @param password String
     * @return int
     */
    @Update("UPDATE company SET password = #{password} WHERE account = #{account}")
    int updateCompanyPassword(@Param("account") String account, @Param("password") String password);

    /**
     * 应聘者修改个人信息
     *
     * @param candidate Candidate
     * @return int
     */
    @Update("UPDATE candidate SET name = #{name}, gender = #{gender}, phone = #{phone}, email = #{email}, " +
            "birthday = #{birthday}, `desc` = #{desc} WHERE account = #{account}")
    int updateCandidate(Candidate candidate);

    /**
     * 公司修改个人信息
     *
     * @param company Company
     * @return int
     */
    @Update("UPDATE company SET name = #{name}, city = #{city}, website = #{website}, code = #{code}, `desc` = #{desc} " +
            "WHERE account = #{account}")
    int updateCompany(Company company);

    /**
     * 根据应聘者获取教育经历
     *
     * @param candidate String
     * @return List<EducationExperience>
     */
    @Select("SELECT * FROM education_experience WHERE candidate = #{candidate}")
    List<EducationExperience> findEducationExperienceByCandidate(@Param("candidate") String candidate);

    /**
     * 插入教育经历
     *
     * @param educationExperience EducationExperience
     * @return int
     */
    @Insert("INSERT INTO education_experience(candidate, school, academic, major, start_date, end_date, `desc`) " +
            "VALUES(#{candidate}, #{school}, #{academic}, #{major}, #{startDate}, #{endDate}, #{desc})")
    int addEducationExperience(EducationExperience educationExperience);

    /**
     * 删除教育经历
     *
     * @param id long
     * @return int
     */
    @Delete("DELETE FROM education_experience WHERE candidate = #{candidate} AND id = #{id}")
    int delEducationExperience(@Param("candidate") String candidate, @Param("id") long id);

    /**
     * 根据应聘者获取期望职位
     *
     * @param candidate String
     * @return List<ExpectedPosition>
     */
    @Select("SELECT * FROM expected_position WHERE candidate = #{candidate}")
    List<ExpectedPosition> findExpectedPositionByCandidate(@Param("candidate") String candidate);

    /**
     * 插入期望职位
     *
     * @param expectedPosition ExpectedPosition
     * @return int
     */
    @Insert("INSERT INTO expected_position(candidate, city, position, salary) " +
            "VALUES(#{candidate}, #{city}, #{position}, #{salary})")
    int addExpectedPosition(ExpectedPosition expectedPosition);

    /**
     * 删除期望职位
     *
     * @param id long
     * @return int
     */
    @Delete("DELETE FROM expected_position WHERE candidate = #{candidate} AND id = #{id}")
    int delExpectedPosition(@Param("candidate") String candidate, @Param("id") long id);

    /**
     * 根据应聘者获取工作经历
     *
     * @param candidate String
     * @return List<WorkExperience>
     */
    @Select("SELECT * FROM work_experience WHERE candidate = #{candidate}")
    List<WorkExperience> findWorkExperienceByCandidate(@Param("candidate") String candidate);

    /**
     * 插入工作经历
     *
     * @param workExperience WorkExperience
     * @return int
     */
    @Insert("INSERT INTO work_experience(candidate, company, department, position, `desc`) " +
            "VALUES(#{candidate}, #{company}, #{department}, #{position}, #{desc})")
    int addWorkExperience(WorkExperience workExperience);

    /**
     * 删除工作经历
     *
     * @param id long
     * @return int
     */
    @Delete("DELETE FROM work_experience WHERE candidate = #{candidate} AND id = #{id}")
    int delWorkExperience(@Param("candidate") String candidate, @Param("id") long id);

}
