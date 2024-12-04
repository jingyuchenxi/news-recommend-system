package com.example.recommend.event.engine.engine;

import com.example.recommend.event.engine.enums.FetchTypeEnum;
import com.example.recommend.event.engine.enums.ParseTypeEnum;
import com.example.recommend.event.engine.enums.SourceTypeEnum;
import com.example.recommend.event.engine.enums.StoreTypeEnum;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:jycx
 * @date: 2024/12/4 11:39
 * @description:数据引擎配置
 */
public final class DataEngineConfig {
    /**
     * 数据源配置
     */
    public static final Map<SourceTypeEnum, String> SOURCE_URI_CONFIG = new HashMap<SourceTypeEnum, String>() {{
        put(SourceTypeEnum.SO_HU, "https://www.sohu.com/");
        put(SourceTypeEnum.PEOPLES_DAILY, "http://paper.people.com.cn/");
        put(SourceTypeEnum.STOCK, "");
    }};

    /**
     * 数据抓取方案配置(支持写入多数据源)
     */
    public static final Map<SourceTypeEnum, FetchTypeEnum> FETCHER_STRATEGY_CONFIG = new HashMap<SourceTypeEnum, FetchTypeEnum>() {{
        put(SourceTypeEnum.SO_HU, FetchTypeEnum.CRAWLER);
        put(SourceTypeEnum.PEOPLES_DAILY, FetchTypeEnum.CRAWLER);
        put(SourceTypeEnum.STOCK, FetchTypeEnum.API);
    }};

    /**
     * 数据解析方案配置
     */
    public static final Map<SourceTypeEnum, ParseTypeEnum> PARSER_STRATEGY_CONFIG = new HashMap<SourceTypeEnum, ParseTypeEnum>() {{
        put(SourceTypeEnum.SO_HU, ParseTypeEnum.SO_HU);
        put(SourceTypeEnum.PEOPLES_DAILY, ParseTypeEnum.PEOPLES_DAILY);
        put(SourceTypeEnum.STOCK, ParseTypeEnum.STOCK);
    }};

    /**
     * 数据存储方案配置(支持写入多数据源)
     */
    public static final Map<SourceTypeEnum, List<StoreTypeEnum>> STORE_STRATEGY_CONFIG = new HashMap<SourceTypeEnum, List<StoreTypeEnum>>() {{
        put(SourceTypeEnum.SO_HU, Lists.newArrayList(StoreTypeEnum.KAFKA));
        put(SourceTypeEnum.PEOPLES_DAILY, Lists.newArrayList(StoreTypeEnum.KAFKA, StoreTypeEnum.MY_SQL));
        put(SourceTypeEnum.STOCK, Lists.newArrayList(StoreTypeEnum.KAFKA, StoreTypeEnum.MY_SQL));
    }};
}