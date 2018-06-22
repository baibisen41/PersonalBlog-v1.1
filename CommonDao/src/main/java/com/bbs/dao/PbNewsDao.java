package com.bbs.dao;

import com.bbs.entity.News;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * User: baibisen
 * Date: 2018/5/21
 */
@Repository
public interface PbNewsDao {

    int saveNewsList(List<News> newsList) throws Exception;

    int saveInitPvList(List<News> newsList) throws Exception;

    List<News> listNewsListByDate(Map<String, Object> map) throws Exception;

    Integer countNewsList() throws Exception;

    News selectNewsById(String id) throws Exception;
}
