package com.example.recommend.event.engine.engine.parser;

import com.example.recommend.event.engine.dto.DataResult;

/**
 * @author:jycx
 * @date: 2024/12/1 18:29
 * @description:数据解析(处理不同数据源)
 */
public interface DataParser<T, R extends DataResult> {
    /**
     * 数据解析,支持[html/json/api/rt]
     *
     * @param cmd
     * @return
     */
    R parse(ParseCmd<T> cmd);
}