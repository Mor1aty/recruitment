package com.lishi.recruitment.mapper;

import com.lishi.recruitment.bean.back.BackCompany;
import com.lishi.recruitment.bean.back.BackJob;
import com.lishi.recruitment.bean.db.Admin;
import com.lishi.recruitment.bean.db.Candidate;
import com.lishi.recruitment.bean.db.JobType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LiShi
 * date: 2021/4/3 17:49
 * description: 管理员 Mapper
 */
public interface AdminMapper {

    /**
     * 根据帐号和密码查询管理员
     *
     * @param account  String
     * @param password String
     * @return Admin
     */
    @Select("SELECT * FROM admin WHERE account = #{account} AND password = #{password}")
    Admin findAdminByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    /**
     * 获取所有应聘者
     *
     * @param pageNum  int
     * @param pageSize int
     * @return List<Candidate>
     */
    @Select("SELECT * FROM candidate LIMIT #{pageNum}, #{pageSize}")
    List<Candidate> findAllCandidate(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    /**
     * 获取所有应聘者数量
     *
     * @return int
     */
    @Select("SELECT COUNT(*) FROM candidate")
    int findAllCandidateCount();

    /**
     * 根据查询获取所有应聘者
     *
     * @param query    String
     * @param pageNum  int
     * @param pageSize int
     * @return List<Candidate>
     */
    @Select("SELECT * FROM candidate WHERE name LIKE #{query} LIMIT #{pageNum}, #{pageSize}")
    List<Candidate> findAllCandidateWithQuery(@Param("query") String query, @Param("pageNum") int pageNum,
                                              @Param("pageSize") int pageSize);

    /**
     * 根据查询获取所有应聘者数量
     *
     * @return int
     */
    @Select("SELECT COUNT(*) FROM candidate WHERE name LIKE #{query}")
    int findAllCandidateCountWithQuery(@Param("query") String query);

    /**
     * 删除应聘者
     *
     * @param candidate String
     * @return int
     */
    @Delete("DELETE FROM candidate WHERE account = #{candidate}")
    int delCandidate(@Param("candidate") String candidate);

    /**
     * 获取所有公司
     *
     * @param pageNum  int
     * @param pageSize int
     * @return List<Candidate>
     */
    @Select("SELECT * FROM company LIMIT #{pageNum}, #{pageSize}")
    List<BackCompany> findAllCompany(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    /**
     * 获取所有公司数量
     *
     * @return int
     */
    @Select("SELECT COUNT(*) FROM company")
    int findAllCompanyCount();

    /**
     * 根据查询获取所有公司
     *
     * @param query    String
     * @param pageNum  int
     * @param pageSize int
     * @return List<Candidate>
     */
    @Select("SELECT * FROM company WHERE name LIKE #{query} LIMIT #{pageNum}, #{pageSize}")
    List<BackCompany> findAllCompanyWithQuery(@Param("query") String query, @Param("pageNum") int pageNum,
                                              @Param("pageSize") int pageSize);

    /**
     * 根据查询获取所有公司数量
     *
     * @return int
     */
    @Select("SELECT COUNT(*) FROM company WHERE name LIKE #{query}")
    int findAllCompanyCountWithQuery(@Param("query") String query);

    /**
     * 删除公司
     *
     * @param candidate String
     * @return int
     */
    @Delete("DELETE FROM company WHERE account = #{candidate}")
    int delCompany(@Param("candidate") String candidate);

    /**
     * 获取所有招聘
     *
     * @param pageNum  int
     * @param pageSize int
     * @return List<BackJob>
     */
    @Select("SELECT * FROM job LIMIT #{pageNum}, #{pageSize}")
    List<BackJob> findAllRecruit(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    /**
     * 获取所有招聘数量
     *
     * @return int
     */
    @Select("SELECT COUNT(*) FROM job")
    int findAllRecruitCount();

    /**
     * 根据查询获取所有招聘
     *
     * @param query    String
     * @param pageNum  int
     * @param pageSize int
     * @return List<BackJob>
     */
    @Select("SELECT * FROM job WHERE name LIKE #{query} LIMIT #{pageNum}, #{pageSize}")
    List<BackJob> findAllRecruitWithQuery(@Param("query") String query, @Param("pageNum") int pageNum,
                                          @Param("pageSize") int pageSize);

    /**
     * 根据查询获取所有招聘数量
     *
     * @return int
     */
    @Select("SELECT COUNT(*) FROM job WHERE name LIKE #{query}")
    int findAllRecruitCountWithQuery(@Param("query") String query);

    /**
     * 删除招聘
     *
     * @param job int
     * @return int
     */
    @Delete("DELETE FROM job WHERE id = #{job}")
    int delRecruit(@Param("job") int job);

    /**
     * 获取所有职位类型
     *
     * @param pageNum  int
     * @param pageSize int
     * @return List<JobType>
     */
    @Select("SELECT * FROM job_type LIMIT #{pageNum}, #{pageSize}")
    List<JobType> findAllJobType(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    /**
     * 获取所有职位类型数量
     *
     * @return int
     */
    @Select("SELECT COUNT(*) FROM job_type")
    int findAllJobTypeCount();

    /**
     * 根据查询获取所有职位类型
     *
     * @param query    String
     * @param pageNum  int
     * @param pageSize int
     * @return List<JobType>
     */
    @Select("SELECT * FROM job_type WHERE name LIKE #{query} LIMIT #{pageNum}, #{pageSize}")
    List<JobType> findAllJobTypeWithQuery(@Param("query") String query, @Param("pageNum") int pageNum,
                                          @Param("pageSize") int pageSize);

    /**
     * 根据查询获取所有职位类型数量
     *
     * @return int
     */
    @Select("SELECT COUNT(*) FROM job_type WHERE name LIKE #{query}")
    int findAllJobTypeCountWithQuery(@Param("query") String query);

    /**
     * 插入职位类型
     *
     * @param name String
     * @return int
     */
    @Insert("INSERT INTO job_type(name) VALUES(#{name})")
    int insertJobType(@Param("name") String name);

    /**
     * 删除职位类型
     *
     * @param id int
     * @return int
     */
    @Delete("DELETE FROM job_type WHERE id = #{id}")
    int delJobType(@Param("id") int id);
}
