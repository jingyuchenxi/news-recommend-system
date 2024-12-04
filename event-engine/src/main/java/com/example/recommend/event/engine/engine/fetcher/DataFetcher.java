package com.example.recommend.event.engine.engine.fetcher;

/**
 * @author:jycx
 * @date: 2024/12/4 12:46
 * @description:数据获取
 */
public interface DataFetcher<T, R> {
    /**
     * 数据查询,支持[crawler/api/rt]
     *
     * @param cmd
     * @return
     */
    R fetch(T cmd);
}