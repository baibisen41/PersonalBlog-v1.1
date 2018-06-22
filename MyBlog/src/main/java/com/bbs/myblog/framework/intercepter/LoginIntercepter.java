package com.bbs.myblog.framework.intercepter;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 大森 on 2017/8/26.
 */
public class LoginIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String url = httpServletRequest.getRequestURI();
        System.out.println(url);
        if (url.contains("home.do") || url.contains("checkLogin.do")) {
            return true;
        }
        HttpSession session = httpServletRequest.getSession();
        String id = (String) session.getAttribute("userid");
        if (!StringUtils.isEmpty(id) && id.equals("2013082401")) {
            return true;
        }
        System.out.println("拦截住了");
        httpServletResponse.sendRedirect("/SchoolServer/mvc/home.do");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
