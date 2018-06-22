package com.bbs.utils;

import java.util.UUID;

/**
 * User: baibisen
 * Date: 2018/5/20
 */
public class UUIDUtils {

    public static String uuidKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
