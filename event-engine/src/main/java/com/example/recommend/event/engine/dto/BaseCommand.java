package com.example.recommend.event.engine.dto;

import com.example.recommend.event.engine.enums.SourceTypeEnum;
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
public class BaseCommand {
    /**
     * 数据源类型
     */
    private SourceTypeEnum sourceType;
}