package com.roc.core.redis;

import com.roc.core.Utils.ClassUtil;
import com.roc.core.Utils.StringUtil;
import redis.clients.jedis.Jedis;

/**
 * User: rocwu
 * Date: 2017/3/9
 * Time: 下午4:23
 * Desc:
 */
public abstract class BaseRedis {

    protected String configName = "";

    /**
     * 业务id
     * @return id
     */
    protected abstract int getBid();

    public void set(String key, String value) {
        Jedis jedis = RDCPManager.openJedis(configName);
        key = formattedKey(key);
        jedis.set(key, value);
        RDCPManager.closeJedis(jedis);
    }

    public String get(String key) {
        Jedis jedis = RDCPManager.openJedis(configName);
        key = formattedKey(key);
        String res = jedis.get(key);
        RDCPManager.closeJedis(jedis);
        return res;
    }

    public void setBean(String key, Object obj) {
        String json = ClassUtil.getJsonFromBean(obj);
        Jedis jedis = RDCPManager.openJedis(configName);
        key = formattedKey(key);
        jedis.set(key, json);
        RDCPManager.closeJedis(jedis);
    }

    public Object getBean(String key, Class<?> c) {
        Jedis jedis = RDCPManager.openJedis(configName);
        key = formattedKey(key);
        String json = jedis.get(key);
        RDCPManager.closeJedis(jedis);
        if (json == null) return null;
        return ClassUtil.getBeanFromJson(json, c);
    }

    public long delete(String key) {
        Jedis jedis = RDCPManager.openJedis(configName);
        key = formattedKey(key);
        long res = jedis.del(key);
        RDCPManager.closeJedis(jedis);
        return res;
    }

    public long delete(String... keys) {
        Jedis jedis = RDCPManager.openJedis(configName);
        for (int i = 0; i < keys.length; i++) {
            keys[i] = formattedKey(keys[i]);
        }
        long res = jedis.del(keys);
        RDCPManager.closeJedis(jedis);
        return res;
    }

    private String formattedKey(String key) {
        if (StringUtil.isEmpty(key))
            throw new RuntimeException("empty key: " + key);
        return getBid() + "-" + key;
    }
}
