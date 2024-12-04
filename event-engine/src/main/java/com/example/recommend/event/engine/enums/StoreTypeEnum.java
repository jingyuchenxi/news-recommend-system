package com.example.recommend.event.engine.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author:jycx
 * @date: 2024/12/1 18:22
 * @description:支持的数据存储类型
 */
@AllArgsConstructor
@Getter
public enum StoreTypeEnum {
    /**
     * MY_SQL
     */
    MY_SQL(0, "MY_SQL"),
    /**
     * KAFKA
     */
    KAFKA(1, "KAFKA"),
    /**
     * HBASE
     */
    HBASE(2, "HBASE"),
    /**
     * ES
     */
    ES(3, "ES"),
    /**
     * HDFS
     */
    HDFS(4, "HDFS"),
    ;

    private final int code;
    private final String desc;
}