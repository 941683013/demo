package com.example.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 将object对象转成json格式字符串
 */
public class GsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
