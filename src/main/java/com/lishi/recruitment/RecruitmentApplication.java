package com.lishi.recruitment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author LiShi
 * date: 2020/12/31 15:02
 * description: 启动类
 */
@SpringBootApplication
@MapperScan("com.lishi.recruitment.mapper")
public class RecruitmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentApplication.class, args);
    }

}
