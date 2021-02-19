package com.lishi.recruitment.mapper;

import com.lishi.recruitment.bean.back.BackJob;
import com.lishi.recruitment.bean.db.Job;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LiShi
 * date: 2021/2/19 11:00
 * description: 招聘 Mapper
 */
public interface RecruitMapper {
    /**
     * 根据条件获取所有招聘信息
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
}
