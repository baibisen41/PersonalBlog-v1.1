package com.bbs.myblog.framework.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 大森 on 2017/11/13.
 */
public class IpUtil {

    //此部分获取用户当前ip地址，后期详细重写
    public static String getIp(HttpServletRequest request, HttpServletResponse response) {
        String strIp = request.getRemoteAddr();
        System.out.println(strIp);
        return strIp;
    }
}
