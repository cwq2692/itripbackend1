package com.cwq.email;

import com.cwq.message.RandUtil;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author:崔文青
 * @Description:
 * @Date:Created in 19:11 2019/5/20
 */
@Component
public class SendEmail {
    @Resource(name = "mailMessage")
    SimpleMailMessage mailMessage;
    @Resource(name = "mailSender")
    JavaMailSenderImpl mailSender;

    public  String sendEmail(String to){
        String randNum = RandUtil.getRandNum();
        mailMessage.setTo(to);//设置收件人
        mailMessage.setSubject("爱旅行邮件激活");
        mailMessage.setText("爱旅行验证"+randNum);

        mailSender.send(mailMessage);
        return randNum;
    }
}
