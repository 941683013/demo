package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Test {
    @Autowired
    private JavaMailSender mailSender;

    //    @Autowired
//    MyMailService mail;
//
    public void send() {
//        mail.sendMail("YDS", "3572424627@qq.com",  "Title", "hello !");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("desyang@qq.com");
        message.setTo("3572424627@qq.com");
        message.setSubject("Title");
        message.setText("content");

        mailSender.send(message);

    }
}


