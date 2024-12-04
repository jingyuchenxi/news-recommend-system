package com.example.recommend.event.engine.engine.fetcher.impl;

import com.example.recommend.event.engine.annotation.FetchType;
import com.example.recommend.event.engine.engine.fetcher.DataFetcher;
import com.example.recommend.event.engine.engine.fetcher.FetchCmd;
import com.example.recommend.event.engine.enums.FetchTypeEnum;
import edu.uci.ics.crawler4j.crawler.Page;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:jycx
 * @date: 2024/12/4 13:35
 */
@FetchType(type = FetchTypeEnum.CRAWLER)
@Slf4j
public class PageFetcher implements DataFetcher<Object, Object> {

    @Override
    public Object fetch(Object cmd) {
        if (cmd instanceof FetchCmd && ((FetchCmd) cmd).getFetchInfo() instanceof Page) {
            return ((FetchCmd) cmd).getFetchInfo();
        }
        return null;
    }
}