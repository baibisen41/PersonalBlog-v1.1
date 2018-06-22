package com.bbs.myblog.businesswork.service;

import com.alibaba.fastjson.JSONArray;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * User: baibisen
 * Date: 2018/4/15
 */
public interface PbBlogService {

    String getBlogList(Map<String, String> map) throws Exception;

    String getNewsList(Map<String, String> map) throws Exception;

    JSONArray getTagList() throws Exception;

    ModelAndView getBlogDetail(Map<String, Object> map) throws Exception;

    JSONArray getBlogRank() throws Exception;

    JSONArray getTypeList() throws Exception;

    JSONArray hotBlogList() throws Exception;

    JSONArray shortNewsList() throws Exception;

    JSONArray recommendBlogList() throws Exception;

    JSONArray replyBlogList() throws Exception;
}
