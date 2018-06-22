package com.bbs.myblog.framework.task;

import org.springframework.stereotype.Component;

/**
 * Created by 大森 on 2017/12/30.
 */
@Component
public class DemoTask {

//    @Autowired
//    public INewsCoreService iNewsCoreService;

/*    private static Logger logger = LoggerFactory.getLogger(DemoTask.class);

    public void testHandler() {
        logger.error("测试！");
    }

    private List<News> spiderDemoHandler() {
        Map<String, List<News>> map = new HashMap<>();
        List<News> newsList = new ArrayList<>();
        News news = null;

        //获取全部文章 共计3000条，下一页根据规律仿写url
        ok:
        for (int i = 1; i <= 17; i++) {
            Document document = null;
            if (i == 1) {
                document = new SpiderTask().getDocument(Common.newsUrl);
            } else {
                document = new SpiderTask().getDocument(Common.newsUrl + "/n/page/" + i + "/");
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

                //重构前爬虫时间判断
                if (DateTimeUtil.isNeedStopSpiderHandler(time))
                    break ok;

                //获取内容详情链接，再次访问该链接，并获取内容详情
                String contentUrl = elementsContent.get(j).getElementsByTag("a").attr("href");
                Document docContent = new SpiderTask().getDocument(Common.newsUrl + contentUrl);

*//*                logger.info((j + 1) + "标题：" + elementsTitle.get(j).text());
                logger.info((j + 1) + "时间：" + elementsTime.get(j).select("span.gray").text());
                logger.info((j + 1) + "描述：" + elementsSummary.get(j).text());
                logger.info((j + 1) + "内容：" + docContent.select("#news_body").select("p").text() + "\n");*//*

                news.setNewsTitle(elementsTitle.get(j).text());
                news.setNewsTime(elementsTime.get(j).select("span.gray").text());
                news.setNewsFrom("博客园");
                news.setNewsSummary(SpecialWordUtil.filterEmoji(elementsSummary.get(j).text()));
                news.setNewsContent(SpecialWordUtil.filterEmoji(String.valueOf(docContent.getElementById("news_body").select("p"))));

 //               logger.info((j + 1) + "文章内容" + String.valueOf(docContent.getElementById("news_body").select("p")));
                newsList.add(news);
            }
        }
        logger.error("本次爬取最新资讯数量：" + newsList.size());
        map.put("newNewsMapKey", newsList);

        return newsList;
    }

    private int saveSpiderHandler(List<News> list) {
        int saveCode = 1;//0代表失败，1代表成功
        try {
            logger.info("存储数量：" + list.size());
//            iNewsCoreService.insertNewNewsList(list);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            saveCode = 0;
        }
        return saveCode;
    }

    public void startSpider() {
        int i = saveSpiderHandler(spiderDemoHandler());
        logger.info("save result:" + i);
    }


    public static void main(String[] args) {
        DemoTask demoTask = new DemoTask();
        demoTask.spiderDemoHandler();
        //       int i = demoTask.saveSpiderHandler(demoTask.spiderDemoHandler());
//        logger.info("save result:" + i);
    }*/
}
