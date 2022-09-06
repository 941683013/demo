package com.example.mapper;

import com.example.entity.Account;

public interface AccountMapper {

    Boolean insertAccount(Account acc); // 注册账户

    String selectAccountByEmail(String email);

    String login(Account acc);

    int updateAccount(Account acc);


}
