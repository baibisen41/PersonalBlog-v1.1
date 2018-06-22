package com.bbs.spider.task;


import com.bbs.dao.PbBlogDao;
import com.bbs.dao.PbNewsDao;
import com.bbs.entity.Blog;
import com.bbs.entity.News;
import com.bbs.utils.DateTimeUtils;
import com.bbs.utils.UUIDUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: baibisen
 * Date: 2018/5/17
 */
public class SpiderTask extends BaseTask {

    @Autowired
    private PbNewsDao pbNewsDao;
    private String url;
    private final static Logger logger = LoggerFactory.getLogger(SpiderTask.class);

    public SpiderTask(String url) {
        this.url = url;
    }

    // 解析页面数据
    private Map<String, List<News>> spiderNewHandler() throws Exception {
        Map<String, List<News>> map = new HashMap<>();
        List<News> newsList = new ArrayList<>();
        News news = null;

        //只获取今日资讯
        ok:
        for (int i = 1; i <= 10; i++) {
            Document document = null;
            if (i == 1) {
                document = getDocument(url);
            } else {
                document = getDocument(url + "/n/page/" + i + "/");
            }

            //抓取标题
            Elements elementsTitle = document.getElementsByClass("news_entry");
            //抓取时间
            Elements elementsTime = document.getElementsByClass("entry_footer");
            //内容概述
            Elements elementsSummary = document.getElementsByClass("entry_summary");
            //内容详情链接
            Elements elementsContent = document.select("div.content").select("h2");
            for (int j = 0; j < elementsTitle.size(); j++) {
                news = new News();
                String time = elementsTime.get(j).select("span.gray").text();
                logger.info(time.substring(0, 10));
                // 只获取本日的资讯，非本日则不存储
                if (!(time.substring(0, 10)).equals(DateTimeUtils.dateFormat(System.currentTimeMillis()))) {
                    break ok;
                }

                //获取内容详情链接，再次访问该链接，并获取内容详情
                String contentUrl = elementsContent.get(j).getElementsByTag("a").attr("href");
                Document docContent = getDocument(url + contentUrl);

                logger.info((j + 1) + "标题：" + elementsTitle.get(j).text());
                logger.info((j + 1) + "时间：" + elementsTime.get(j).select("span.gray").text());
                logger.info((j + 1) + "缩略图：" + elementsSummary.get(j).getElementsByTag("img").attr("src"));
                logger.info((j + 1) + "描述：" + elementsSummary.get(j).text());
                logger.info((j + 1) + "内容：" + docContent.select("#news_body").select("p").text() + "\n");

                news.setId(UUIDUtils.uuidKey());
                news.setTitle(elementsTitle.get(j).text());
                news.setCreateDate(elementsTime.get(j).select("span.gray").text());
                news.setTag("资讯");
                news.setThumbPic("https:" + elementsSummary.get(j).getElementsByTag("img").attr("src"));
                news.setSummary(elementsSummary.get(j).text());
                news.setContent(String.valueOf(docContent.getElementById("news_body").select("p")));

                newsList.add(news);
            }
        }
        pbNewsDao.saveNewsList(newsList);
        pbNewsDao.saveInitPvList(newsList);
        logger.error("本次爬取最新资讯数量：" + newsList.size());
        map.put("newNewsMapKey", newsList);
        return map;
    }

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring-app.xml");
//        new SpiderTask("https://news.cnblogs.com").spiderNewHandler();
        SpiderTask spiderTask = context.getBean(SpiderTask.class);
        spiderTask.spiderNewHandler();
    }

}
