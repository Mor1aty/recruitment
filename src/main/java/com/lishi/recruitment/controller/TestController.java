package com.lishi.recruitment.controller;

import com.lishi.recruitment.bean.Test;
import com.lishi.recruitment.service.TestService;
import com.lishi.recruitment.wrap.WrapMapper;
import com.lishi.recruitment.wrap.Wrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiShi
 * date: 2020/12/31 15:19
 * description: 测试 Controller
 */
@RestController
@RequestMapping("test")
@AllArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("test1")
    public Wrapper<Test> test() {
        return WrapMapper.okObtain("查询成功", testService.test());
    }
}
