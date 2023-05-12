package com.blog.utils;

import java.util.ArrayList;
import java.util.List;

public class IntIdConverter {
    public static List<String> convert(List<Integer> ids) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(String.valueOf(ids.get(i)));
        }
        return result;
    }
}