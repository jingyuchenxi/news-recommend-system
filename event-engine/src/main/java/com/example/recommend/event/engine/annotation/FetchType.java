package com.example.recommend.event.engine.annotation;

import com.example.recommend.event.engine.enums.FetchTypeEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author:jycx
 * @date: 2024/12/1 18:26
 * @description:数据获取注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface FetchType {
    FetchTypeEnum type();
}