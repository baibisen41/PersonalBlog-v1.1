package com.bbs.myblog.framework.task;

import org.springframework.stereotype.Component;

/**
 * Created by 大森 on 2017/12/10.
 */
@Component
public class SpiderTask {

 /*   private static Logger logger = LoggerFactory.getLogger(SpiderTask.class);

    private static volatile boolean isFinish = false;

    //爬虫每次爬取的最后时间
    private String spiderHotMark;
    private String spiderNewMark;
    private boolean isNewFirst = true;
    private boolean isHotFirst = true;

    @Autowired
    private INewsCoreService iNewsCoreService;

    @Resource
    private JedisPool jedisPool;

    protected Document getDocument(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).timeout(10000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }*/

    public void startFake() {
//        logger.info("假定时任务");
    }

    //由于现在数据量不大，可以每两个小时重新抓取一次，并存储，如果后期爬取数据量大的网站再进行优化
/*    public void startSpiderHandler() {
        List<News> newsList = new ArrayList<>();

        //临时改成同步
        int i = saveContentHandler(0, spiderNewHandler());
        logger.info("存储结果：" + i);


        int j = saveContentHandler(1, spiderHotHandler());
        logger.info("存储结果：" + j);


*//*        new Thread(new Runnable() {
            @Override
            public void run() {
                saveContentHandler(0, spiderNewHandler());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                saveContentHandler(1, spiderHotHandler());
            }
        }).start();*//*
    }

    private boolean isNeedStop(int from, String getTime) {
        logger.info("isNeedStop getTime:" + getTime);
        boolean isNeedStop = false;
        JedisUtil jedisUtil = JedisUtil.getInstance();
        if (from == Common.newsNew) {
            logger.info("from:" + Common.newsNew + "; redis time:" + jedisUtil.get(jedisPool, "newMark"));
            if (getTime.equals(jedisUtil.get(jedisPool, "newMark"))) {
                isNeedStop = true;
            }
        } else if (from == Common.newsHot) {
            logger.info("from:" + Common.newsHot + "; redis time:" + jedisUtil.get(jedisPool, "hotMark"));
            if (getTime.equals(jedisUtil.get(jedisPool, "hotMark"))) {
                isNeedStop = true;
            }
        }
        return isNeedStop;
    }

    private boolean isSaveFinish(int from, String getTime) {
        logger.info("isSaveFinish time:" + getTime);
        boolean isSaveOk = true;
        try {
            JedisUtil jedisUtil = JedisUtil.getInstance();
            if (from == Common.newsNew) {
                if (!StringUtils.isEmpty(jedisUtil.get(jedisPool, "newMark"))) {
                    logger.info("newMark:" + jedisUtil.get(jedisPool, "newMark"));
                    jedisUtil.del(jedisPool, "newMark");
                }
                jedisUtil.set(jedisPool, "newMark", getTime);
            } else if (from == Common.newsHot) {
                if (!StringUtils.isEmpty(jedisUtil.get(jedisPool, "hotMark"))) {
                    logger.info("hotMark:" + jedisUtil.get(jedisPool, "hotMark"));
                    jedisUtil.del(jedisPool, "hotMark");
                }
                jedisUtil.set(jedisPool, "hotMark", getTime);
            } else {
                isSaveOk = false;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            isSaveOk = false;
        }
        return isSaveOk;
    }

    private Map<String, List<News>> spiderNewHandler() {
        Map<String, List<News>> map = new HashMap<>();
        List<News> newsList = new ArrayList<>();
        News news = null;

        //获取全部文章 共计3000条，下一页根据规律仿写url
        ok:
        for (int i = 1; i <= 20; i++) {
            Document document = null;
            if (i == 1) {
                document = getDocument(Common.newsUrl);
            } else {
                document = getDocument(Common.newsUrl + "/n/page/" + i + "/");
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

                if (isNewFirst) {
                    boolean result = isSaveFinish(Common.newsNew, time);
                    logger.error("from:" + Common.newsNew + ";mark结果:" + result);
                    isNewFirst = false;
                }

                //重构前爬虫时间判断
                if (DateTimeUtil.isNeedStopSpiderHandler(time))
                    break ok;

//                if (isNeedStop(Common.newsNew, time))
//                    break ok;

                //获取内容详情链接，再次访问该链接，并获取内容详情
                String contentUrl = elementsContent.get(j).getElementsByTag("a").attr("href");
                Document docContent = getDocument(Common.newsUrl + contentUrl);

*//*                logger.info((j + 1) + "标题：" + elementsTitle.get(j).text());
                logger.info((j + 1) + "时间：" + elementsTime.get(j).select("span.gray").text());
                logger.info((j + 1) + "描述：" + elementsSummary.get(j).text());
                logger.info((j + 1) + "内容：" + docContent.select("#news_body").select("p").text() + "\n");*//*

                news.setNewsId(KeyIdUtil.getId());
                news.setNewsTitle(elementsTitle.get(j).text());
                news.setNewsTime(elementsTime.get(j).select("span.gray").text());
                news.setNewsFrom("博客园");
                news.setNewsSummary(SpecialWordUtil.filterEmoji(elementsSummary.get(j).text()));
//                news.setNewsContent(SpecialWordUtil.filterEmoji(docContent.select("#news_body").select("p").text()));
                news.setNewsContent(SpecialWordUtil.filterEmoji(String.valueOf(docContent.getElementById("news_body").select("p"))));
                newsList.add(news);
            }
        }
        logger.error("本次爬取最新资讯数量：" + newsList.size());
        map.put("newNewsMapKey", newsList);
        return map;
    }

    private Map<String, List<News>> spiderHotHandler() {
        Map<String, List<News>> map = new HashMap<>();
        List<News> newsList = new ArrayList<>();
        List<News> topNewsList = new ArrayList<>();
        News news = null;
        int count = 0;

        //获取全部文章 共计3000条，下一页根据规律仿写url
        ok:
        for (int i = 1; i <= 27; i++) {
            Document document = null;
            if (i == 1) {
                document = getDocument(Common.newsHotUrl);
            } else {
                document = getDocument(Common.newsHotUrl + "?page=" + i);
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
                //获取内容详情链接，再次访问该链接，并获取内容详情
                String contentUrl = elementsContent.get(j).getElementsByTag("a").attr("href");
                Document docContent = getDocument(Common.newsUrl + contentUrl);
                String time = elementsTime.get(j).select("span.gray").text();

                if (isHotFirst) {
                    logger.error("first time:" + time);
                    boolean result = isSaveFinish(Common.newsHot, time);
                    logger.error("from:" + Common.newsHot + "mark结果：" + result);
                    isHotFirst = false;
                }

//                if (isNeedStop(Common.newsHot, time))
//                    break ok;

*//*                logger.info((j + 1) + "标题：" + elementsTitle.get(j).text());
                logger.info((j + 1) + "时间：" + elementsTime.get(j).select("span.gray").text());
                logger.info((j + 1) + "描述：" + elementsSummary.get(j).text());
                logger.info((j + 1) + "内容：" + docContent.select("#news_body").select("p").text() + "\n");*//*
//                String time = elementsTime.get(j).select("span.gray").text();

                news.setNewsId(KeyIdUtil.getId());
                news.setNewsTitle(elementsTitle.get(j).text());
                news.setNewsTime(elementsTime.get(j).select("span.gray").text());
                news.setNewsFrom("博客园");
                news.setNewsSummary(SpecialWordUtil.filterEmoji(elementsSummary.get(j).text()));
                news.setNewsContent(SpecialWordUtil.filterEmoji(String.valueOf(docContent.getElementById("news_body").select("p"))));
                newsList.add(news);

            }
        }
//        map.put("topNewsMapKey", topNewsList);
        logger.error("本次爬取热门新闻数量：" + newsList.size());
        map.put("hotNewsMapKey", newsList);
        return map;
    }

    *//**
     * 后期加入时间标识，防止存入重复的数据
     *
     * @param from
     * @param newsMap
     * @return
     *//*
    private synchronized int saveContentHandler(int from, Map<String, List<News>> newsMap) {
        int saveResultCode = 200;
        //暂时先屏蔽 等项目重构完再重新打开2018.4.22
//        try {
//            logger.error("本次处理来自：" + from + " 的请求");
//            ObjectMapper objectMapper = new ObjectMapper();
//            JedisUtil jedisUtil = JedisUtil.getInstance();
//
//            if (from == 0) {
//
//                int result = iNewsCoreService.insertNewNewsList(newsMap.get("newNewsMapKey"));
//                logger.info("SpiderTask插入最新资讯结果：" + result);
//            } else {
//
//                int result = iNewsCoreService.insertHotNewsList(newsMap.get("hotNewsMapKey"));
//                logger.info("SpiderTask插入最新资讯结果：" + result);
//            }
//        } catch (Throwable e) {
//            e.printStackTrace();
//            saveResultCode = 500;
//        }
        return saveResultCode;
    }

    public static void main(String[] args) {
        new SpiderTask().startSpiderHandler();
    }*/

}
