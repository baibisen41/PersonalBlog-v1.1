package com.bbs.myblog.businesswork.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bbs.dao.PbBlogDao;
import com.bbs.dao.PbNewsDao;
import com.bbs.entity.Blog;
import com.bbs.entity.News;
import com.bbs.myblog.businesswork.result.PbBlogDetailResult;
import com.bbs.myblog.businesswork.service.PbBlogService;

import com.bbs.utils.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 文章相关业务
 * User: baibisen
 * Date: 2018/4/15
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PbBlogServiceImpl extends BaseServiceImpl implements PbBlogService {

    @Autowired
    private PbBlogDao pbBlogDao;
    @Autowired
    private PbNewsDao pbNewsDao;

    private final static Logger LOG = LoggerFactory.getLogger(PbBlogServiceImpl.class);

    /**
     * 拉取博客列表 暂时都按时间排序
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public String getBlogList(Map<String, String> map) throws Exception {
        final Integer blogType = Integer.parseInt(map.get("blogType"));
        LOG.info("blogServiceImpl:{}", blogType);
        Integer pageNum = 1;
        Integer pageStart, pageEnd;
        if (!StringUtils.isEmpty(map.get("pageNum"))) {
            pageNum = Integer.parseInt(map.get("pageNum"));
        }
        LOG.info("blogServiceImpl pageNum:{}", pageNum);
        List<Blog> blogList = pbBlogDao.listBlogByType(blogType, (pageNum - 1) * 10);
        LOG.info("blog size:{}", blogList.size());
        if (blogList.size() <= 6) {
            pageStart = 1;
            pageEnd = blogList.size();
        } else {
            if (pageNum > 3) {
                pageStart = pageNum - 2;
                pageEnd = pageNum + 2;
            } else {
                pageStart = 1;
                pageEnd = 6;
            }
        }

        Integer countBlogList = pbBlogDao.countBlogList(blogType);
        LOG.info("blogServiceImpl countBlogList:{}", countBlogList);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("pageNum", pageNum);
        resultMap.put("pageStart", pageStart);
        resultMap.put("pageEnd", pageEnd);
        resultMap.put("totalPages", countBlogList);
        resultMap.put("blogList", blogList);
        LOG.info("blogList:{}", JSON.toJSONString(resultMap));
        return JSON.toJSONString(resultMap);
    }

    /**
     * 拉取新闻资讯列表
     */
    @Override
    public String getNewsList(Map<String, String> map) throws Exception {
        Integer pageNum = 1;
        Integer pageStart, pageEnd;
        if (!StringUtils.isEmpty(map.get("pageNum"))) {
            pageNum = Integer.parseInt(map.get("pageNum"));
        }
        LOG.info("blogServiceImpl pageNum:{}", pageNum);
        Map<String, Object> newsMap = new HashMap<>(2);
        newsMap.put("date", DateTimeUtils.dateFormat(System.currentTimeMillis()));
        newsMap.put("pageNum", (pageNum - 1) * 20);
        List<News> blogList = pbNewsDao.listNewsListByDate(newsMap);
        LOG.info("blog size:{}", blogList.size());
        if (blogList.size() <= 6) {
            pageStart = 1;
            pageEnd = blogList.size();
        } else {
            if (pageNum > 3) {
                pageStart = pageNum - 2;
                pageEnd = pageNum + 2;
            } else {
                pageStart = 1;
                pageEnd = 6;
            }
        }
        Integer countNewsList = pbNewsDao.countNewsList();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("pageNum", pageNum);
        resultMap.put("pageStart", pageStart);
        resultMap.put("pageEnd", pageEnd);
        resultMap.put("totalPages", countNewsList);
        resultMap.put("blogList", blogList);
        LOG.info("blogList:{}", JSON.toJSONString(resultMap));
        return JSON.toJSONString(resultMap);
    }

    /**
     * 标签列表
     *
     * @return
     * @throws Exception
     */
    @Override
    public JSONArray getTagList() throws Exception {
        return JSONArray.parseArray(JSON.toJSONString(pbBlogDao.listTagCount()));
    }

    /**
     * 文章详情
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelAndView getBlogDetail(Map<String, Object> map) throws Exception {
        final String blogId = String.valueOf(map.get("blogId"));
        final String blogType = String.valueOf(map.get("blogType"));
        PbBlogDetailResult result = new PbBlogDetailResult();
        if (Objects.equals("3", blogType)) {
            News news = pbNewsDao.selectNewsById(blogId);
            if (news != null) {
                result.setId(news.getId());
                result.setTitle(news.getTitle());
                result.setTag(news.getTag());
                result.setContent(news.getContent());
                result.setBlogPv(news.getBlogPv());
                result.setCreateDate(news.getCreateDate());
            }
        } else {
            Blog blog = pbBlogDao.getBlogById(blogId);
            if (blog != null) {
                result.setId(blog.getId());
                result.setTitle(blog.getTitle());
                result.setTag(blog.getTag());
                result.setUserName(blog.getUserName());
                result.setContent(blog.getContent());
                result.setBlogPv(blog.getBlogPv());
                result.setCreateDate(blog.getCreateDate());
            }
        }
        return new ModelAndView().addObject("blogDetail", result);
    }

    /**
     * 文章排行榜
     *
     * @return
     * @throws Exception
     */
    @Override
    public JSONArray getBlogRank() throws Exception {
        return JSONArray.parseArray(JSONObject.toJSONString(pbBlogDao.listBlogByCount()));
    }

    @Override
    public JSONArray getTypeList() throws Exception {
        List<String> resultList = new ArrayList<>();
        resultList.add("专业技术");
        resultList.add("生活感悟");
        resultList.add("资讯头条");
        return JSONArray.parseArray(JSONObject.toJSONString(resultList));
    }

    @Override
    public JSONArray hotBlogList() throws Exception {
        JSONArray jsonArray = null;
        List<Blog> hotBlogList = pbBlogDao.listHotBlog();
        if (hotBlogList != null && !hotBlogList.isEmpty()) {
            jsonArray = JSONArray.parseArray(JSONObject.toJSONString(hotBlogList));
        }
        return jsonArray;
    }

    @Override
    public JSONArray shortNewsList() throws Exception {
        JSONArray jsonArray = null;
        List<Blog> shortNewsList = pbBlogDao.listShortNews();
        if (shortNewsList != null && !shortNewsList.isEmpty()) {
            jsonArray = JSONArray.parseArray(JSONObject.toJSONString(shortNewsList));
        }
        return jsonArray;
    }

    @Override
    public JSONArray recommendBlogList() throws Exception {
        JSONArray jsonArray = null;
        List<Blog> recommendBlogList = pbBlogDao.listRecommendBlog();
        if (recommendBlogList != null && !recommendBlogList.isEmpty()) {
            jsonArray = JSONArray.parseArray(JSONObject.toJSONString(recommendBlogList));
        }
        return jsonArray;
    }

    @Override
    public JSONArray replyBlogList() throws Exception {
        JSONArray jsonArray = null;
        List<Blog> replyBlogList = pbBlogDao.listReplyBlog();
        if (replyBlogList != null && !replyBlogList.isEmpty()) {
            jsonArray = JSONArray.parseArray(JSONObject.toJSONString(replyBlogList));
        }
        return jsonArray;
    }
}
