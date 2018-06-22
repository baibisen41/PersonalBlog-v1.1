package com.bbs.dao;

import com.bbs.entity.Blog;
import com.bbs.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: baibisen
 * Date: 2018/4/21
 */
@Repository
public interface PbBlogDao {

    Integer save(Blog blog) throws Exception;

    Integer saveList(List<Blog> blogList) throws Exception;

    List<Blog> listBlogByType(@Param(value = "blogType") Integer blogType, @Param(value = "pageNum") Integer pageNum) throws Exception;

    List<Tag> listTagCount() throws Exception;

    Blog getBlogById(String id) throws Exception;

    List<Blog> listBlogByCount() throws Exception;

    Integer countBlogList(@Param("type") Integer status) throws Exception;

    List<Blog> listHotBlog() throws Exception;

    List<Blog> listShortNews() throws Exception;

    List<Blog> listRecommendBlog() throws Exception;

    List<Blog> listReplyBlog() throws Exception;
}
