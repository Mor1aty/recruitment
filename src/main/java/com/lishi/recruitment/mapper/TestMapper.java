package com.lishi.recruitment.mapper;

import com.lishi.recruitment.bean.Test;
import org.apache.ibatis.annotations.Select;

/**
 * @author LiShi
 * date: 2020/12/31 15:17
 * description: 测试 Mapper
 */
public interface TestMapper {

    /**
     * 测试
     * @return Test
     */
    @Select("SELECT * FROM test LIMIT 0,1")
    Test test();
}
