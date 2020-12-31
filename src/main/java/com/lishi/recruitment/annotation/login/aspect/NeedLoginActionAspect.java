package com.lishi.recruitment.annotation.login.aspect;


import com.lishi.recruitment.annotation.login.storage.Token;
import com.lishi.recruitment.annotation.login.utils.TokenUtil;
import com.lishi.recruitment.constant.CustomConstant;
import com.lishi.recruitment.wrap.WrapMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author LiShi
 * date: 2020/12/31 14:22
 * description: NeedLogin 注解切面处理
 */
@Aspect
@Component
public class NeedLoginActionAspect {

    @Pointcut("@annotation(com.lishi.recruitment.annotation.login.aspect.NeedLogin)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object actionAround(ProceedingJoinPoint pjp) throws Throwable {
        // 获取当前正在执行的类
        Class<?> classTarget = pjp.getTarget().getClass();
        // 获取当前正在执行的方法
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 获取 request
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 获取注解参数
        String tokenCode = classTarget.getMethod(signature.getName(), signature.getParameterTypes()).getAnnotation(NeedLogin.class).value();
        long level = classTarget.getMethod(signature.getName(), signature.getParameterTypes()).getAnnotation(NeedLogin.class).level();

        // 调用方法验证 request 的 Authorization
        Token token = TokenUtil.checkToken(request.getHeader("authorization"));
        if (token == null) {
            return WrapMapper.error(CustomConstant.Wrap.CODE_TOKEN_FAILURE, CustomConstant.Wrap.MSG_TOKEN_FAILED);
        }
        if (level != -1 && level != token.level) {
            return WrapMapper.error(CustomConstant.Wrap.CODE_LEVEL_FAILURE, CustomConstant.Wrap.MSG_LEVEL_FAILED);
        }

        request.setAttribute(tokenCode + "_loginToken", token);
        return pjp.proceed();
    }
}
