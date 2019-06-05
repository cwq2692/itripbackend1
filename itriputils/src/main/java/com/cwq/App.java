package com.cwq;


import com.cwq.redis.RedisAPI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext app=new ClassPathXmlApplicationContext("application-redis.xml");
        RedisAPI redisAPI = (RedisAPI)app.getBean("redis");
        redisAPI.set("name","adasfafa");
    }
}
