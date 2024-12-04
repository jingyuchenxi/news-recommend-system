package com.example.recommend.event.engine.engine.store.impl;

import com.example.recommend.event.engine.annotation.StoreType;
import com.example.recommend.event.engine.config.KafkaProducer;
import com.example.recommend.event.engine.dto.DataResult;
import com.example.recommend.event.engine.engine.store.DataStore;
import com.example.recommend.event.engine.enums.StoreTypeEnum;
import com.example.recommend.event.engine.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @author:jycx
 * @date: 2024/12/4 11:33
 * @description:
 */
@StoreType(type = StoreTypeEnum.KAFKA)
public class KafkaStore implements DataStore {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public boolean store(DataResult payload) {
        if (Objects.isNull(payload)) {
            return true;
        }
        kafkaProducer.sendMessage("news_topic", JacksonUtil.toJson(payload));
        return true;
    }
}