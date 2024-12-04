package com.example.recommend.event.engine.engine.store;

import com.example.recommend.event.engine.annotation.StoreType;
import com.example.recommend.event.engine.dto.DataResult;
import com.example.recommend.event.engine.engine.DataEngineConfig;
import com.example.recommend.event.engine.enums.SourceTypeEnum;
import com.example.recommend.event.engine.enums.StoreTypeEnum;
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
 * @date: 2024/12/4 13:25
 * @description:数据解析引擎
 */
@Component
@Slf4j
public class StoreEngine implements InitializingBean {
    private static Map<StoreTypeEnum, DataStore> storeMap = Maps.newHashMap();

    @Autowired
    private List<DataStore> stores;

    /**
     * 数据存储
     *
     * @param data
     */
    public void store(SourceTypeEnum sourceType, DataResult data) {
        // todo 这里可以并发写入(此处串行写入示例)
        List<StoreTypeEnum> storeTypes = DataEngineConfig.STORE_STRATEGY_CONFIG.get(sourceType);
        for (StoreTypeEnum storeType : storeTypes) {
            DataStore dataStore = storeMap.get(storeType);
            if (Objects.isNull(dataStore)) {
                log.warn("no data store for {}", sourceType);
                return;
            }
            dataStore.store(data);
        }
        log.info("data store success, sourceType:{}, data:{}, storeTypes:{}", sourceType, data, storeTypes);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        stores.forEach(store -> {
            Class<?> clazz = AopUtils.isAopProxy(store) ? AopUtils.getTargetClass(store) : store.getClass();
            StoreType annotation = clazz.getAnnotation(StoreType.class);
            if (Objects.nonNull(annotation)) {
                storeMap.put(annotation.type(), store);
            }
        });
    }
}