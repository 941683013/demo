package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MyMailService {
    @Autowired
    JavaMailSender javaMailSender;
    public void sendMail(String from,String to,String subject,String text){
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom("desyang@qq.com");
        smm.setFrom(from);//发送者
        smm.setTo(to);//收件人
        smm.setSubject(subject);//邮件主题
        smm.setText(text);//邮件内容
        javaMailSender.send(smm);//发送邮件
    }
}

