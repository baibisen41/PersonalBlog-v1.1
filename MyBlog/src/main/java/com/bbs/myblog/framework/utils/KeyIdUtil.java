package com.bbs.myblog.framework.utils;


import java.util.UUID;

/**
 * Created by 大森 on 2017/11/12.
 */
public class KeyIdUtil {

    public synchronized static String getId() {
        String strId = UUID.randomUUID().toString();
        return strId.substring(0, 13).replaceAll("-", "");
    }

    public static void main(String[] args) {
        int i = 0;
        while (i < 100) {
            System.out.print(getId() + "\n");
            i++;
        }
    }
}
