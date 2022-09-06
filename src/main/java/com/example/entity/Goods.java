package com.example.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Goods {
    int label;     // 主键
    int fromer;    // 发布者
    long timer;    // 发布时间
    String idea;   // 描述
    double prices; // 价格
}
