package com.example.recommend.event.engine.engine.parser;

import com.example.recommend.event.engine.annotation.ParseType;
import com.example.recommend.event.engine.dto.DataResult;
import com.example.recommend.event.engine.engine.DataEngineConfig;
import com.example.recommend.event.engine.enums.ParseTypeEnum;
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
public class ParseEngine implements InitializingBean {
    private static Map<ParseTypeEnum, DataParser> parserMap = Maps.newHashMap();

    @Autowired
    private List<DataParser> parsers;

    public DataResult parse(ParseCmd cmd) {
        ParseTypeEnum parseType = DataEngineConfig.PARSER_STRATEGY_CONFIG.get(cmd.getSourceType());
        DataParser parser = parserMap.get(parseType);
        if (Objects.isNull(parser)) {
            log.warn("no data parser for {}", cmd.getSourceType());
            return null;
        }
        DataResult data = parser.parse(cmd);
        log.info("data parse success, sourceType:{}, data:{}", cmd.getSourceType(), data);
        return data;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        parsers.forEach(parser -> {
            Class<?> clazz = AopUtils.isAopProxy(parser) ? AopUtils.getTargetClass(parser) : parser.getClass();
            ParseType annotation = clazz.getAnnotation(ParseType.class);
            if (Objects.nonNull(annotation)) {
                parserMap.put(annotation.type(), parser);
            }
        });
    }
}