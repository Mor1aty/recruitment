package com.lishi.recruitment.config;

import com.lishi.recruitment.config.filter.CrossDomainFilter;
import com.lishi.recruitment.config.filter.EncodingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;


/**
 * @author LiShi
 * date: 2020/12/31 15:09
 * description: Server 配置
 */
@Configuration
public class ServerConfig {

    /**
     * 注册编码过滤器
     *
     * @return FilterRegistrationBean<EncodingFilter>
     */
    @Bean
    public FilterRegistrationBean<EncodingFilter> encodingFilter() {
        FilterRegistrationBean<EncodingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new EncodingFilter());
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return registrationBean;
    }

    /**
     * 注册跨域过滤器
     *
     * @return FilterRegistrationBean<CrossDomainFilter>
     */
    @Bean
    public FilterRegistrationBean<CrossDomainFilter> crossDomainFilter() {
        FilterRegistrationBean<CrossDomainFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CrossDomainFilter());
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return registrationBean;
    }

}
