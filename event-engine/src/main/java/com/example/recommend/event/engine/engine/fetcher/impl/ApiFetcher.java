package com.example.recommend.event.engine.engine.fetcher.impl;

import com.example.recommend.event.engine.annotation.FetchType;
import com.example.recommend.event.engine.engine.fetcher.DataFetcher;
import com.example.recommend.event.engine.engine.fetcher.FetchCmd;
import com.example.recommend.event.engine.enums.FetchTypeEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * @author:jycx
 * @date: 2024/12/4 13:35
 * @description:接口类数据
 */
@FetchType(type = FetchTypeEnum.API)
public class ApiFetcher implements DataFetcher<Object, Object> {

    @Override
    public Object fetch(Object cmd) {
        if (cmd instanceof FetchCmd && ((FetchCmd) cmd).getFetchInfo() instanceof String) {
            return ((FetchCmd) cmd).getFetchInfo();
        }
        return StringUtils.EMPTY;
    }
}