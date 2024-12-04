package com.example.recommend.event.engine.engine.parser;

import com.example.recommend.event.engine.dto.BaseCommand;
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
public class ParseCmd<T> extends BaseCommand {
    private T parseInfo;
}