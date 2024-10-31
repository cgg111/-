package com.example.repair_severs.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.UUID;
import java.util.logging.Logger;

@Component  //要想初始化host 需要加
public class redis_static {
    private static redis_static instance;
    //    申明了一个静态的单例模式instance
        public static String host = "127.0.0.1"; //线上
//      public static String password = "luowen1969"; //线上

public static void main(String[] args) {
    String tokens=UUID.randomUUID().toString();
    System.out.println(tokens);
    redis_static.getIntence().addTimeout(tokens,"luowenxing",10000);
}

    public static int port = 6379;
    public static int timeout = 5000;
    public static int maxIdle = 5000;
    public static long maxWaitMillis = 5000;
    public static int selectdb = 0;

    //实例化redis_static
    public static redis_static getIntence() {
        if (instance == null) {
//            打印日志
            Logger.getGlobal().info("初始化成功");
//            实例化
            instance = new redis_static();
        }
        return instance;
    }
    // 公共方法返回的是一个JedisPool对象 管理Redis连接池。连接池可以高效地管理和复用Redis连接，避免频繁创建和销毁连接带来的开销。
    public JedisPool getjedisPool() {
        //创建一个新的对象用于对redis链接池的配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置连接池中最大空闲连接数
        jedisPoolConfig.setMaxIdle(maxIdle);
        //设置从连接池中获取连接时的最大等待时间如若超过抛出异常
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        //创建jedisPool对象来对线程池来高效管理
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        return jedisPool;
    }
    // 添加键和值
    public Long addtoken(String token, String account) {
        JedisPool jedisPool = getjedisPool();
        Jedis jedis = jedisPool.getResource();
//        选择数据库
        jedis.select(selectdb);
        try {
            jedis.set(token, account);//添加键值
            jedis.expire(token, 360000);//设置过期时间
        } catch (Exception ex) {
            Logger.getGlobal().info("redis_static:" + ex.toString());//异常处理
        } finally {
            jedis.close();
            jedisPool.close();
            // 关闭实例释放资源
        }
        return null;
    }

    public Long saveINCR(String prefixOrder, int timeout) {
        Long orderId = null;//初始化后续用于存储增加后的id
        JedisPool jedisPool = getjedisPool();
        Jedis jedis = jedisPool.getResource();
        jedis.select(selectdb);
        try {
            orderId = jedis.incr(prefixOrder);//进行递增
            jedis.expire(prefixOrder, timeout);
        } catch (Exception ex) {
            Logger.getGlobal().info("redis_static:" + ex.toString());
        } finally {
            jedis.close();
            jedisPool.close();
        }
        return orderId;
    }
    //存储一个键值对，并为这个键设置过期时间
    public Long addTimeout(String token, String account, int timeout) {
        JedisPool jedisPool = getjedisPool();
        Jedis jedis = jedisPool.getResource();
        jedis.select(selectdb);
        try {
            jedis.set(token, account);

            jedis.expire(token, timeout);
        } catch (Exception ex) {
            Logger.getGlobal().info("redis_static:" + ex.toString());

        } finally {
            jedis.close();
            jedisPool.close();
        }
        return null;
    }
    //从 Redis 中获取与指定键关联的值
    public String getsrc(String key) {
        JedisPool jedisPool = getjedisPool();
        Jedis jedis = jedisPool.getResource();
        jedis.select(selectdb);
        String k = null;
        try {
            String sbScript = "return redis.call('get','" + key + "')";
            k = (String) jedis.eval(sbScript);
        } catch (Exception ex) {
            Logger.getGlobal().info("redis_static:" + ex.toString());
        } finally {
            jedis.close();
            jedisPool.close();
        }
        return k;
    }

    //删除
    public String delsrc(String key) {
        JedisPool jedisPool = getjedisPool();
        Jedis jedis = jedisPool.getResource();
        jedis.select(selectdb);


        String k = null;

        try {
            String sbScript = "return redis.call('del','" + key + "')";
            Logger.getGlobal().info("删除key" + sbScript);
            k = jedis.eval(sbScript).toString();
        } catch (Exception ex) {
            Logger.getGlobal().info("redis_static:" + ex.toString());
        } finally {
            jedis.close();
            jedisPool.close();
        }
        return k;
    }
    //创建
    public Long addsrc(String key, String val) {
        JedisPool jedisPool = getjedisPool();
        Jedis jedis = jedisPool.getResource();
        jedis.select(selectdb);
        Long k = jedis.setnx(key, val);
        jedis.close();
        jedisPool.close();
        return k;
    }


}
