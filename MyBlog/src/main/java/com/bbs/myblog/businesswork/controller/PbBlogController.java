package com.bbs.myblog.businesswork.controller;

import com.bbs.myblog.businesswork.common.*;
import com.bbs.myblog.businesswork.service.PbBlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 博客中心
 * User: baibisen
 * Date: 2018/4/27
 */
@Controller
public class PbBlogController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(PbBlogController.class);

    @Autowired
    private PbBlogService pbBlogService;

    /**
     * 显示主页博客列表
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/showBlogList")
    public void showBlogList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1.获取参数
        final String pageNum = getRequestParams(request, "pageNum");
        final String blogType = getRequestParams(request, "blogType");
        logger.info("pageNum{} , blogType:{}", pageNum, blogType);
        // 2.执行service
        Map<String, String> map = new HashMap<>();
        map.put("pageNum", pageNum);
        System.out.println();
        String blogList = null;
        if (blogType.equals("3")) {
            blogList = pbBlogService.getNewsList(map);
        } else {
            map.put("blogType", blogType);
            blogList = pbBlogService.getBlogList(map);
        }
        response.getWriter().write(blogList);
    }

    /**
     * 博客详情
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/showBlogDetail")
    public ModelAndView showBlogDetail(HttpServletRequest request) throws Exception {
        final String blogId = getRequestParams(request, "id");
        final String blogType = getRequestParams(request, "type");
        logger.info("选择博客id:{}", blogId);

        Map<String, Object> map = new HashMap<>();
        map.put("blogId", blogId);
        map.put("blogType", blogType);
        //访问量 + 文章详情
        ModelAndView modelAndView = pbBlogService.getBlogDetail(map);
        modelAndView.setViewName(ViewRegistration.PB_BLOG_DETAIL);
        return modelAndView;
    }

    /**
     * 标签云
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/showTagList", method = RequestMethod.GET)
    @ResponseBody
    public void showTagList(HttpServletResponse response) throws Exception {
        response.getWriter().write(pbBlogService.getTagList().toString());
    }

    /**
     * 文章排行榜
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/showBlogRank")
    public void showBlogRank(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().write(pbBlogService.getBlogRank().toString());
    }

    /**
     * 点击排行
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/hotBlogList")
    @ResponseBody
    public void hotBlogList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("hotBlogList:{}", pbBlogService.hotBlogList().toString());
        response.getWriter().write(pbBlogService.hotBlogList().toString());
    }

    /**
     * 最新资讯
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/shortNewsList")
    @ResponseBody
    public void shortNewsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().write(pbBlogService.shortNewsList().toString());
    }

    /**
     * 站长推荐
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/recommendBlogList")
    @ResponseBody
    public void recommendBlogList(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        response.getWriter().write(pbBlogService.recommendBlogList().toString());
    }

    /**
     * 最新评论
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/replyBlogList")
    @ResponseBody
    public void replyBlogList(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        response.getWriter().write(pbBlogService.replyBlogList().toString());
    }

    /**
     * 文章分类
     * @param response
     * @throws Exception
     */
    @RequestMapping("/showTypeList")
    public void showTypeList(HttpServletResponse response) throws Exception {
        response.getWriter().write(pbBlogService.getTypeList().toString());
    }

}
