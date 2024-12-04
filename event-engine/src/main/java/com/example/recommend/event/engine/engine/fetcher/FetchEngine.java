package com.example.recommend.event.engine.engine.fetcher;

import com.example.recommend.event.engine.annotation.FetchType;
import com.example.recommend.event.engine.engine.DataEngineConfig;
import com.example.recommend.event.engine.enums.FetchTypeEnum;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author:jycx
 * @date: 2024/12/4 13:05
 * @description:数据查询引擎
 */
@Component
@Slf4j
public class FetchEngine implements InitializingBean {
    private static Map<FetchTypeEnum, DataFetcher> fetcherMap = Maps.newHashMap();

    @Autowired
    private List<DataFetcher> fetchers;

    public Object fetch(FetchCmd cmd) {
        FetchTypeEnum fetchType = DataEngineConfig.FETCHER_STRATEGY_CONFIG.get(cmd.getSourceType());
        DataFetcher fetcher = fetcherMap.get(fetchType);
        if (Objects.isNull(fetcher)) {
            log.warn("no data fetcher for {}", cmd.getSourceType());
            return null;
        }
        Object data = fetcher.fetch(cmd);
        log.info("data fetch success, sourceType:{}, data:{}", cmd.getSourceType(), data);
        return data;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        fetchers.forEach(fetcher -> {
            Class<?> clazz = AopUtils.isAopProxy(fetcher) ? AopUtils.getTargetClass(fetcher) : fetcher.getClass();
            FetchType annotation = clazz.getAnnotation(FetchType.class);
            if (Objects.nonNull(annotation)) {
                fetcherMap.put(annotation.type(), fetcher);
            }
        });
    }
}