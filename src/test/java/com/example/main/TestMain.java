package com.example.main;

import com.example.mapper.UserMapper;
import com.example.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class TestMain {

    public static void main(String[] args) {
        SqlSession session = MybatisUtil.getSqlSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        StringBuffer str = new StringBuffer();
        mapper.selectAllUser().forEach(System.out::println);
    }
}
