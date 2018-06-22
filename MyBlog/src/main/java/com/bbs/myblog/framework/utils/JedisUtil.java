package com.bbs.myblog.framework.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Created by 大森 on 2017/12/2.
 */
@Component
public class JedisUtil {

    private JedisPool jedisPool;

    private Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    public JedisUtil() {
    }

    //饿汉模式
    private static class SingletonHolder {
        private static final JedisUtil jedisUtil = new JedisUtil();
    }

    public static final JedisUtil getInstance() {
        return SingletonHolder.jedisUtil;
    }

    public Jedis getJedis(JedisPool jedisPool) {
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    public void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public void shutdown() {
        if (jedisPool != null) {
            jedisPool.destroy();
        }
    }

    public String get(JedisPool jedisPool, String key) {
        Jedis jedis = null;
        String result = "";
        try {
            jedis = getJedis(jedisPool);
            if (jedis != null) {
                result = jedis.get(key);
            } else {
                logger.error("get opt connection null error!");
            }
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            logger.error("get value connect error:", e);
        } finally {
            close(jedis);
        }
        return result;
    }

    public void set(JedisPool jedisPool, String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis(jedisPool);
            if (jedis != null) {
                jedis.set(key, value);
            } else {
                logger.error("set opt connection null error!");
            }
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            logger.error("set connect error:", e);
        } finally {
            close(jedis);
        }
    }

    public void del(JedisPool jedisPool, String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis(jedisPool);
            if (jedis != null) {
                jedis.del(key);
            } else {
                logger.error("del opt connection null error!");
            }
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedis.close();
                jedis = null;
            }
            logger.error("set connect error:", e);
        } finally {
            close(jedis);
        }
    }
}

