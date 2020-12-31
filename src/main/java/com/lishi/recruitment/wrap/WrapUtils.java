package com.lishi.recruitment.wrap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lishi.recruitment.utils.ValueUtils;
import com.lishi.recruitment.constant.CustomConstant;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import java.io.IOException;

import java.util.Collection;
import java.util.Enumeration;


/**
 * @author LiShi
 * date: 2020/12/31 14:18
 * description: 封装处理工具类
 */
public class WrapUtils {

    private WrapUtils() {

    }

    /**
     * request 参数写入 WrapParams
     *
     * @param request HttpServletRequest
     * @return WrapParams
     */
    public static WrapParams paramToWrapParams(HttpServletRequest request) throws IOException, ServletException {

        JSONObject jsonObject = new JSONObject();
        ServletInputStream is = request.getInputStream();
        String paramString = ValueUtils.convertStreamToString(is);

        if (ValueUtils.valNotEmpty(paramString)) {
            jsonObject = JSON.parseObject(paramString);
        } else {
            boolean isForm = request.getHeader(CustomConstant.Response.CONTENT_TYPE) != null &&
                    (request.getHeader(CustomConstant.Response.CONTENT_TYPE).contains("multipart/form-data")
                            || request.getHeader(CustomConstant.Response.CONTENT_TYPE).contains("multipart/mixed"));
            if (isForm) {
                Collection<Part> parts = request.getParts();
                for (Part part : parts) {
                    if (ValueUtils.valNotEmpty(part.getSubmittedFileName())) {
                        jsonObject.put(part.getName(), part);
                    }
                }
            }

            Enumeration<String> enu = request.getParameterNames();
            while (enu.hasMoreElements()) {
                String tempEnu = enu.nextElement();
                jsonObject.put(tempEnu, request.getParameter(tempEnu));
            }
        }

        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attrName = attributeNames.nextElement();
            if (attrName.contains("_loginToken")) {
                jsonObject.put(attrName.split("_")[0], request.getAttribute(attrName));
            }
        }

        return new WrapParams(jsonObject);
    }


}
