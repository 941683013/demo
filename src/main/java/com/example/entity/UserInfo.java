package com.example.entity;

import lombok.Data;

@Data
public class UserInfo {

    int id;         // 账号
    String nickname;// 昵称
    String sex;     // 性别
    long birthday;  // 出生
    String words;   // 备注
    String addres;  // 住址
    String school;  // 学校
    String head;    // 头像 url
}
