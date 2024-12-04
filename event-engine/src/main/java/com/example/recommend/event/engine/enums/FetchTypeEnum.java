package com.example.recommend.event.engine.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author:jycx
 * @date: 2024/12/1 18:22
 * @description:数据获取类型
 */
@AllArgsConstructor
@Getter
public enum FetchTypeEnum {
    /**
     * CRAWLER(PAGE类型)
     */
    CRAWLER(0, "CRAWLER"),
    /**
     * API
     */
    API(1, "API"),
    ;

    private final int code;
    private final String desc;
}