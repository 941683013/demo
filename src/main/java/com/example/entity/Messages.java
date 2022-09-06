package com.example.entity;

import lombok.Data;

@Data
public class Messages {
    int tag;            // 消息id
    int fromer;         // 发送者
    int touser;         // 接受者
    String content;     // 内容
    long timer;         // 时间
    boolean isRead;     // 已读
    boolean isDelete;   // 已删除
}
