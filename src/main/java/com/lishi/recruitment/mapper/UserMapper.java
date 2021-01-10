package com.lishi.recruitment.mapper;

import com.lishi.recruitment.bean.db.Candidate;
import com.lishi.recruitment.bean.db.Company;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
