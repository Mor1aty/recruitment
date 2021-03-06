package com.lishi.recruitment.mapper;

import com.lishi.recruitment.bean.back.BackCompany;
import com.lishi.recruitment.bean.back.BackJob;
import com.lishi.recruitment.bean.back.BackProgress;
import com.lishi.recruitment.bean.db.Job;
import com.lishi.recruitment.bean.db.JobType;
import com.lishi.recruitment.bean.db.Progress;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author LiShi
 * date: 2021/2/19 11:00
 * description: 招聘 Mapper
 */
public interface RecruitMapper {
    /**
     * 根据条件获取招聘信息
     *
     * @param condition String
     * @return List<Job>
     */
    @Select("SELECT j.id, j.name, j.type, t.name AS typeName, j.company, c.name AS companyName, j.`desc`, j.min_salary, " +
            "j.max_salary, j.city " +
            "FROM job j LEFT JOIN job_type t ON j.type = t.id LEFT JOIN company c ON j.company = c.account " +
            "${condition}")
    List<BackJob> findRecruitByCondition(@Param("condition") String condition);

    /**
     * 获取符合条件的招聘信息数量
     *
     * @param condition String
     * @return List<Job>
     */
    @Select("SELECT COUNT(*) " +
            "FROM job j LEFT JOIN job_type t ON j.type = t.id LEFT JOIN company c ON j.company = c.account " +
            "${condition}")
    long findRecruitCountByCondition(@Param("condition") String condition);

    /**
     * 根据条件获取公司信息
     *
     * @param condition String
     * @return List<Job>
     */
    @Select("SELECT name, city, website, `desc` FROM company ${condition}")
    List<BackCompany> findCompanyByCondition(@Param("condition") String condition);

    /**
     * 获取符合条件的公司信息数量
     *
     * @param condition String
     * @return List<Job>
     */
    @Select("SELECT COUNT(*) FROM company ${condition}")
    long findCompanyCountByCondition(@Param("condition") String condition);

    /**
     * 插入招聘信息
     *
     * @param job Job
     * @return int
     */
    @Insert("INSERT INTO job(name, type, company, `desc`, min_salary, max_salary, city) " +
            "VALUES(#{name}, #{type}, #{type}, #{company}, #{desc}, #{minSalary}, #{maxSalary}, #{city})")
    int insertJob(Job job);

    /**
     * 更新招聘信息
     *
     * @param job Job
     * @return int
     */
    @Update("UPDATE job SET name = #{name}, type = #{type}, `desc` = #{desc}, min_salary = #{minSalary}, " +
            "max_salary = #{maxSalary}, city = #{city} WHERE id = #{id} AND company = #{company}")
    int updateJob(Job job);

    /**
     * 删除招聘信息
     *
     * @param id      int
     * @param company String
     * @return int
     */
    @Update("DELETE FROM job WHERE id = #{id} AND company = #{company}")
    int deleteJob(@Param("id") int id, @Param("company") String company);

    /**
     * 根据工作获取招聘进度
     *
     * @param job int
     * @return List<BackProgress>
     */
    @Select("SELECT p.id, p.job, j.name AS jobName, p.candidate AS candidateAccount, c.name AS candidateName, c.gender AS candidateGender, p.progress " +
            "FROM progress p LEFT JOIN candidate c ON p.candidate = c.account " +
            "LEFT JOIN job j ON p.job = j.id WHERE p.job = #{job}")
    List<BackProgress> findProgressByJob(@Param("job") int job);

    /**
     * 根据应聘者获取招聘进度
     *
     * @param candidate String
     * @return List<BackProgress>
     */
    @Select("SELECT p.id, p.job, j.name AS jobName, p.candidate AS candidateAccount, c.name AS candidateName, c.gender AS candidateGender, p.progress " +
            "FROM progress p LEFT JOIN candidate c ON p.candidate = c.account " +
            "LEFT JOIN job j ON p.job = j.id WHERE p.candidate = #{candidate}")
    List<BackProgress> findProgressByCandidate(@Param("candidate") String candidate);

    /**
     * 更新招聘进度
     *
     * @param id     int
     * @param result int
     * @return int
     */
    @Update("UPDATE progress SET progress = #{progress} WHERE id = #{id}")
    int updateProgress(@Param("id") int id, @Param("result") int result);

    /**
     * 插入招聘进度
     *
     * @param progress Progress
     * @return int
     */
    @Insert("INSERT INTO progress(job, candidate, progress) VALUES(#{job}, #{candidate}, #{progress})")
    int insertProgress(Progress progress);

    /**
     * 获取所有职位类型
     *
     * @return List<JobType>
     */
    @Select("SELECT * FROM job_type")
    List<JobType> findAllJobType();
}
