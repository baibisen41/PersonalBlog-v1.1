package com.bbs.myblog.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 大森 on 2017/9/18.
 */
public class DateTimeUtil {

    private static Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

    // 毫秒 -> 年月日
    public static String getDateHandler(long time) {
        String strTime = "";
        if (time != 0) {
            Date nowTime = new Date(time);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            strTime = dateFormat.format(nowTime);
        }
        return strTime;
    }

    // 年月日 -> 毫秒
    public static long toDateHandler() {
        return System.currentTimeMillis();
    }

    public static long revertHandler(String time) {
        long datetime = 0;
        try {
            if (!time.isEmpty()) {
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time);
                datetime = date.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datetime;
    }

    /*********** 爬虫部分使用，其他地方勿调用  start *******/
    //这部分影响效率，后期有时间可以优化
    //该类主要用来为爬虫判断爬取的信息是否超过了7天，如果是则立即停止爬取
    public static boolean isNeedStopSpiderHandler(String getTime) {
        logger.info("spider get time is " + getTime);
        boolean isNeedStop = false;
        long needStopTime = 0;
        long getSpiderTime = revertHandler(getTime);
        logger.info("revert time is " + getSpiderTime);

        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        long currentMillis = calendar.getTimeInMillis();
        logger.info("today is " + currentDay);
        if (currentDay > 7) {
            needStopTime = getNormalTime(currentMillis);
            logger.info("get Time:" + needStopTime);
        } else {
            //兼容跨月和跨年的情况
            needStopTime = getOverTime();
            logger.info("get Time:" + needStopTime);
        }

        if (getSpiderTime < needStopTime) {
            isNeedStop = true;
        }
        logger.error("spider time is:" + getDateHandler(getSpiderTime) + "；should stop time is:" + getDateHandler(needStopTime));
        return isNeedStop;
    }

    private static long getNormalTime(long currentMillis) {
        return (currentMillis - 604800000);
    }

    private static long getOverTime() {
        long needStopTime = 0;
        String revertTime = "";
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int diffDay = currentDay - 7;
        logger.error("相差天数：" + diffDay);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;

        if (currentMonth == 3) {
            //判断闰年
            if (currentYear % 4 == 0 && currentYear % 100 != 0) {
                revertTime = currentYear + "-" + (currentMonth - 1) + "-" + (29 + diffDay) + " 00:00";
            } else {
                revertTime = currentYear + "-" + (currentMonth - 1) + "-" + (28 + diffDay) + " 00:00";
            }
        } else if (currentMonth == 1) {
            revertTime = (currentYear - 1) + "-12-" + (31 + diffDay) + " 00:00";
        } else if (currentMonth == 5 || currentMonth == 7 || currentMonth == 8 || currentMonth == 10 || currentMonth == 12) {
            revertTime = currentYear + "-" + (currentMonth - 1) + "-" + (30 + diffDay) + " 00:00";
        } else {
            revertTime = currentYear + "-" + (currentMonth - 1) + "-" + (31 + diffDay) + " 00:00";
        }
        logger.error("目标日期：" + revertTime);
        needStopTime = revertHandler(revertTime);

        return needStopTime;
    }
    /********** end ************/
}
