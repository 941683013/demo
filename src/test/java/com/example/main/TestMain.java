package com.example.main;

import com.example.demo.Test;
import com.example.util.JWTUtils;

public class TestMain {

    public static void main(String[] args) {
//        SqlSession session = MybatisUtil.getSqlSession(true);
//        UserMapper mapper = session.getMapper(UserMapper.class);
//        StringBuffer str = new StringBuffer();
//        mapper.selectAllUser().forEach(System.out::println);
//        MyMailService mail = new MyMailService();
//        mail.sendMail("YDS", "3572424627@qq.com",  "Title", "hello !");

        System.out.println(JWTUtils.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEwMDAyIiwiZXhwIjoxNjYyNjM4MTYxfQ.TmZ1U5-Ho6sRdiGPYrsZreHzG1h0itvYy7Nh69TiC4Y"));

    }
}
