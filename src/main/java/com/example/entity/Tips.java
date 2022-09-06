package com.example.entity;

import lombok.Data;

@Data
public class Tips {
    int status;
    String content;
    public Tips(int status, String content) {
        this.status = status;
        this.content = content;
    }
}
