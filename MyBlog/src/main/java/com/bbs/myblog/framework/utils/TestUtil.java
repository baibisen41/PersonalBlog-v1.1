package com.bbs.myblog.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by 大森 on 2017/12/15.
 */
public class TestUtil {



    public static void main(String[] args) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:MM");
        try {
            System.out.println(simpleDateFormat.parse("2017-12-15 00:00").getTime());
            System.out.println(simpleDateFormat.parse("2017-12-11 00:00").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
/*        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:MM");
        try {
            long currentTime = simpleDateFormat.parse("2017-12-15 00:00").getTime();
            System.out.println("当前时间：" + currentTime);
            long goalTime = simpleDateFormat.parse("2017-12-08 00:00").getTime();
//            System.out.println("目标时间：" + simpleDateFormat.format(currentTime - 604800000));
            long getTime = currentTime - goalTime;
            System.out.println("时间段：" + getTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }

}
