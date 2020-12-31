package com.lishi.recruitment.config.filter;


import com.lishi.recruitment.constant.CustomConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author LiShi
 * date: 2020/12/31 15:10
 * description: 处理全站编码过滤器
 */
public class EncodingFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // 转化ServletRequest为HttpRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        filterChain.doFilter(new MyRequest(request), response);
    }

    /**
     * 包装设计模式对request进行处理
     */
    static class MyRequest extends HttpServletRequestWrapper {
        private final HttpServletRequest request;

        public MyRequest(HttpServletRequest request) {
            // 用super传入一个被增强对象
            super(request);
            this.request = request;
        }

        // 覆盖实现方法
        @Override
        public String getParameter(String name) {
            String value = this.request.getParameter(name);
            if (!CustomConstant.Response.GET.equalsIgnoreCase(request.getMethod())) {
                return value;
            }
            if (value == null) {
                return null;
            }
            try {
                return new String(value.getBytes(StandardCharsets.UTF_8), request.getCharacterEncoding());
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
