package com.lishi.recruitment.service;

import com.lishi.recruitment.bean.Test;
import com.lishi.recruitment.mapper.TestMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author LiShi
 * date: 2020/12/31 15:18
 * description: 测试 Service
 */
@Service
@AllArgsConstructor
public class TestService {

    private final TestMapper testMapper;

    /**
     * 测试
     * @return Test
     */
    public Test test() {
        return testMapper.test();
    }
}
