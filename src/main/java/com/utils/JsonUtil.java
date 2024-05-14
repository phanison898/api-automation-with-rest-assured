package com.utils;

import com.google.gson.Gson;

public class JsonUtil {

    private static Gson gson = null;

    public static String convertToJson(Object object) {

        if (gson == null) {
            gson = new Gson();
        }

        return gson.toJson(object);
    }

    public static String convertToJson(String property, String value) {

        return String.format("{\"%s\":\"%s\"}", property, value);
    }

    public static String convertToJson(String property, int value) {

        return String.format("{\"%s\": %s}", property, value);
    }
}
