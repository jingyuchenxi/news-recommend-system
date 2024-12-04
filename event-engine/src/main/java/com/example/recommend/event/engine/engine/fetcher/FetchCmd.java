package com.example.recommend.event.engine.engine.fetcher;

import com.example.recommend.event.engine.dto.BaseCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author:jycx
 * @date: 2024/12/1 17:32
 * @description:获取信息的入参
 */
@Getter
@Setter
@ToString
public class FetchCmd<T> extends BaseCommand {
    /**
     * 不同数据源，获取信息需要的入参不同
     */
    private T fetchInfo;
}