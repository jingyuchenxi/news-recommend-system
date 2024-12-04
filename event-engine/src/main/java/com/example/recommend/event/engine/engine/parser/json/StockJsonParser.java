package com.example.recommend.event.engine.engine.parser.json;

import com.example.recommend.event.engine.annotation.ParseType;
import com.example.recommend.event.engine.dto.DataResult;
import com.example.recommend.event.engine.enums.ParseTypeEnum;
import com.example.recommend.event.engine.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:jycx
 * @date: 2024/12/1 18:33
 * @description:股市详情解析
 */
@ParseType(type = ParseTypeEnum.STOCK)
@Slf4j
public class StockJsonParser extends AbstractJsonParser {

    @Override
    protected DataResult doParse(String payload) {
        log.info("[StockJsonParser][doParse] parse success! payload:{}", payload);
        return JacksonUtil.fromJson(payload, DataResult.class);
    }
}