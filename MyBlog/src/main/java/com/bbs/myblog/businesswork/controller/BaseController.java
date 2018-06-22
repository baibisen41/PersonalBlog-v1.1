package com.bbs.myblog.businesswork.controller;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * User: baibisen
 * Date: 2018/4/21
 */
public class BaseController {

    public String getRequestParams(HttpServletRequest request, String params) {
        if (!StringUtils.isEmpty(request)) {
            return request.getParameter(params);
        }
        return null;
    }

}
