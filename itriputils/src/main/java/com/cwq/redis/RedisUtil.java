//package com.cwq.redis;
//
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import javax.annotation.Resource;
//
///**
// * @Author:崔文青
// * @Description:
// * @Date:Created in 21:31 2019/5/21
// */
//@Component("redis")
//public class RedisUtil {
//    @Resource(name = "jedisPool")
//    JedisPool jedisPool;
//
//    Jedis jedis;
//    //开启连接
//    public Jedis openJedis(){
//        return jedisPool.getResource();
//    }
//    //存储键值对
//    public void set(String key,String value){
//        //获取redis连接
//        jedis = openJedis();
//        jedis.set(key,value);
//        //关闭jedis连接：将jedis连接返回给jedispoll
//        colseJedis();
//    }
//    //关闭连接的方法
//    public void colseJedis(){
//        jedisPool.returnResource(jedis);
//    }
//
//}
