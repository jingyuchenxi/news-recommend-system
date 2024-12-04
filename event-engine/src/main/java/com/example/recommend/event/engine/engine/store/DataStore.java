package com.example.recommend.event.engine.engine.store;

import com.example.recommend.event.engine.dto.DataResult;

/**
 * @author:jycx
 * @date: 2024/12/4 10:56
 * @description:数据存储(支持不同存储方案)
 */
public interface DataStore<T extends DataResult> {
    /**
     * 数据存储
     *
     * @param payload
     * @return
     */
    boolean store(T payload);
}