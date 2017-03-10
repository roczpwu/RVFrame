package com.roc.core.redis;
import com.roc.core.Utils.StringUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: rocwu
 * Date: 2017/3/9
 * Time: 下午3:06
 * Desc: redis连接连接池管理类
 */
public class RDCPManager {
    private static JedisPool defaultpool;
    private static HashMap<String, JedisPool> poolMap;
    private static ConcurrentHashMap<Integer, JedisPool> belongMap;

    static {
        poolMap = new HashMap<String, JedisPool>();
        belongMap = new ConcurrentHashMap<Integer, JedisPool>();
    }

    /**
     * 根据配置名称得到redis连接
     * @return
     */
    public static Jedis openJedis(String configName) {
        if (configName == null)
            configName = "";
        Jedis conn = null;
        JedisPool jedisPool = poolMap.get(configName);
        if (null == jedisPool) {
            String propertitiesPath = "rdcpconfig." + configName + ".properties";
            if (StringUtil.isEmpty(configName))
                propertitiesPath = "rdcpconfig.properties";
            Properties properties = getProperties(propertitiesPath);
            jedisPool = getJedisPool(properties);
            poolMap.put(configName, jedisPool);
        }
        if (null != jedisPool) {
            conn = jedisPool.getResource();
            belongMap.put(conn.hashCode(), jedisPool);
        }
        if (conn == null) {
            System.out.println("CONM为NULL!");
        }
        return conn;
    }

    public static void closeJedis(Jedis jedis) {
        JedisPool pool = belongMap.get(jedis.hashCode());
        belongMap.remove(jedis.hashCode());
        pool.returnResource(jedis);
    }

    /**
     * 获得数据源
     * @param properties
     * @return
     */
    private static JedisPool getJedisPool(Properties properties) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(Integer.parseInt((String) properties.get("maxActive")));
        poolConfig.setMaxIdle(Integer.parseInt((String) properties.get("maxIdle")));
        poolConfig.setMaxWaitMillis(Integer.parseInt((String) properties.get("maxWait")));
        poolConfig.setTestOnBorrow(true);
        String ip = (String) properties.get("serverIp");
        int port = Integer.parseInt((String) properties.get("serverPort"));
        JedisPool pool = new JedisPool(poolConfig, ip, port);
        return pool;
    }

    private static Properties getProperties(String propertiesFileName) {
        Properties properties = new Properties();
        //读取配置文件
        InputStream is = RDCPManager.class.getClassLoader().getResourceAsStream(propertiesFileName);
        try {
            properties.load(is);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return properties;
    }
}
