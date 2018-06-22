package com.bbs.myblog.businesswork.common;

/**
 * User: baibisen
 * Date: 2018/4/14
 */
public class Common {

    /**
     * 爬虫url
     */
    public final static String newsUrl = "https://news.cnblogs.com/";

    public final static String newsHotUrl = "https://news.cnblogs.com/n/digg";

    /**
     * 拉取全部文章来源标识
     */
    public final static int blogAllType = 1001;

    public final static int blogTechType = 1002;

    public final static int blogLifeType = 1003;

    public final static int blogNewsType = 1004;

    /**
     * 各页面拉取信息标识：home  blog  life
     */
    public final static int blogListFromId = 2001;

    public final static int lifeListFromId = 2002;

    /**
     * 资讯头条页标识
     */
    public final static int newsNew = 3001;

    public final static int newsHot = 3002;

    /**
     * 博客详情页标识 分为 博客类和资讯类
     */
    public final static int blogDetailFlag = 4001;

    public final static int newsDetailFlag = 4002;


}
