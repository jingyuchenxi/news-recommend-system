package com.example.recommend.event.engine.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:jycx
 * @date: 2024/12/3 21:19
 */
@Slf4j
public final class JacksonUtil {

    public static String toJson(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("[JacksonUtil][toJson]error, obj:{}", obj, e);
        }
        return null;
    }

    public static <T> T fromJson(String str, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.error("[JacksonUtil][toJson]error, str:{}", str, e);
        }
        return null;
    }
}