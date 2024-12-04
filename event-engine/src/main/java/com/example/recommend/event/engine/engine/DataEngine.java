package com.example.recommend.event.engine.engine;

import com.example.recommend.event.engine.dto.DataRequest;

/**
 * @author:jycx
 * @date: 2024/12/4 10:42
 */
public interface DataEngine {
    /**
     * 数据引擎编排
     *
     * @param req
     */
    void process(DataRequest req);
}