package com.example.recommend.event.engine.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author:jycx
 * @date: 2024/12/1 18:22
 * @description:数据解析器类型
 */
@AllArgsConstructor
@Getter
public enum ParseTypeEnum {
    /**
     * 人民日报
     */
    PEOPLES_DAILY(0, "人民日报"),
    /**
     * 搜狐
     */
    SO_HU(1, "搜狐"),
    /**
     * 股市行情
     */
    STOCK(2, "股市行情"),
    ;

    private final int code;
    private final String desc;
}