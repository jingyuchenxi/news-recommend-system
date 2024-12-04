package com.example.recommend.event.engine.engine.parser.html;

import com.example.recommend.event.engine.dto.DataResult;
import com.example.recommend.event.engine.engine.parser.DataParser;
import com.example.recommend.event.engine.engine.parser.ParseCmd;
import edu.uci.ics.crawler4j.crawler.Page;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:jycx
 * @date: 2024/12/1 19:12
 * @description:html页面处理模版
 */
@Slf4j
public abstract class AbstractPageParser implements DataParser<Page, DataResult> {
    /**
     * 页面解析逻辑
     *
     * @param page
     * @return
     */
    protected abstract DataResult doParse(Page page);

    @Override
    public DataResult parse(ParseCmd<Page> cmd) {
        DataResult data = doParse(cmd.getParseInfo());
        log.info("[AbstractPageParser][parse] finish, parseInfo:{}, data:{}", cmd.getParseInfo(), data);
        return data;
    }
}