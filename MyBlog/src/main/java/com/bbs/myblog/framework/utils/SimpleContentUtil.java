package com.bbs.myblog.framework.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 大森 on 2017/11/12.
 */
public class SimpleContentUtil {

    static Logger logger = LoggerFactory.getLogger(SimpleContentUtil.class);

    public static String getSimpleContent(String content) {
        if (!content.isEmpty()) {
            if (content.length() > 50) {
                content = content.substring(0, 50);
                logger.info("content length=" + content.length() + ";content over 50:" + content);
                return content;
            }
            logger.info("content less 200:" + content);
            return content;
        }
        logger.info("content null" + content);
        return "";
    }

}
