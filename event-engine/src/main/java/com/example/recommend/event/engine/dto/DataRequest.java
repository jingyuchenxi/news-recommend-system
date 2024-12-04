package com.example.recommend.event.engine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author:jycx
 * @date: 2024/12/1 17:32
 */
@Getter
@Setter
@ToString
public class DataRequest<T> extends BaseCommand {
    /**
     * 数据载体
     */
    private T payload;
}