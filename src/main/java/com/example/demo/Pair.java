package com.example.demo;

import lombok.Getter;

@Getter
public class Pair {
    long first;
    String second;
    public Pair(long first, String second) { // timestamp @qq.com
        this.first = first;
        this.second = second;
    }
}
