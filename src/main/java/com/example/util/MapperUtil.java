package com.example.util;

import com.example.mapper.AccountMapper;
import com.example.mapper.UserInfoMapper;
import com.example.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;

public class MapperUtil {

    public static UserMapper getUserMapper(boolean autocommit) {
        SqlSession session = MybatisUtil.getSqlSession(autocommit);
        return session.getMapper(UserMapper.class);
    }

    public static AccountMapper getAccountMapper(boolean autocommit) {
        SqlSession session = MybatisUtil.getSqlSession(autocommit);
        return session.getMapper(AccountMapper.class);
    }

    public static UserInfoMapper getUserInfoMapper(boolean autocommit) {
        SqlSession session = MybatisUtil.getSqlSession(autocommit);
        return session.getMapper(UserInfoMapper.class);
    }
}
