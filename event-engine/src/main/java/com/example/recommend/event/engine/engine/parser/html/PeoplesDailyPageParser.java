package com.example.recommend.event.engine.engine.parser.html;

import com.example.recommend.event.engine.annotation.ParseType;
import com.example.recommend.event.engine.dto.DataResult;
import com.example.recommend.event.engine.enums.ParseTypeEnum;
import edu.uci.ics.crawler4j.crawler.Page;

/**
 * @author:jycx
 * @date: 2024/12/1 18:33
 * @description:人民日报解析
 */
@ParseType(type = ParseTypeEnum.PEOPLES_DAILY)
public class PeoplesDailyPageParser extends AbstractPageParser {

    @Override
    protected DataResult doParse(Page page) {
        return null;
    }
}