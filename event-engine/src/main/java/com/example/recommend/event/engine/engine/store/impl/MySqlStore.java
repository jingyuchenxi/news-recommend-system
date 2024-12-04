package com.example.recommend.event.engine.engine.store.impl;

import com.example.recommend.event.engine.annotation.StoreType;
import com.example.recommend.event.engine.dto.DataResult;
import com.example.recommend.event.engine.engine.store.DataStore;
import com.example.recommend.event.engine.enums.StoreTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author:jycx
 * @date: 2024/12/4 11:33
 */
@StoreType(type = StoreTypeEnum.MY_SQL)
@Slf4j
public class MySqlStore implements DataStore {

    @Override
    public boolean store(DataResult payload) {
        if (Objects.isNull(payload)) {
            return true;
        }
        // todo 数据落库
        log.info("[MySqlStore][store] data save success !, payload:{}", payload);
        return true;
    }
}