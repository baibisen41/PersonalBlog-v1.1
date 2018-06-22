package com.bbs.myblog.businesswork.service.Impl;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * User: baibisen
 * Date: 2018/4/22
 */
public class BaseServiceImpl {

    /**
     * 返回ModelAndView对象
     *
     * @return
     */
    public ModelAndView toModelAndView(Map<String, Object> resultMap) {
        return null;
    }

    /**
     * @return
     * @ResponseBody 返回Json对象
     */
    public String toViewJson() {
        return null;
    }
}
