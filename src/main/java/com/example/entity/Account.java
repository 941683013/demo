package com.example.entity;

import lombok.Data;
import lombok.Setter;

@Setter
@Data
public class Account {

    String email; // 邮箱
    int id;       // 自动生成的账号
    String cypher;// 密码

}
