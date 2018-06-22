package com.bbs.spider.task;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * User: baibisen
 * Date: 2018/5/17
 */
public class BaseTask {

    protected Document getDocument(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).timeout(10000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
