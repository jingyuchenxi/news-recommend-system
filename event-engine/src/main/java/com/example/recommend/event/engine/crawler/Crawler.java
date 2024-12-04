package com.example.recommend.event.engine.crawler;

import com.example.recommend.event.engine.dto.DataRequest;
import com.example.recommend.event.engine.engine.DataEngine;
import com.example.recommend.event.engine.enums.SourceTypeEnum;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

/**
 * @author:jycx
 * @date: 2024/12/4 15:21
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Crawler extends WebCrawler {

    private SourceTypeEnum sourceType;
    private DataEngine dataEngine;

    private static final Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|zip|gz))$");

    /**
     * 页面合法性 or 重复校验
     * 这里可以增加非法页面过滤逻辑(通过redis记录黑名单 or 布隆过滤器) and 页面url去重逻辑
     *
     * @param page
     * @param url
     * @return
     */
    protected boolean ruleCheck(Page page, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches();
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        boolean permit = ruleCheck(referringPage, url);
        log.warn("[AbstractPageParser][parser]visit,permit:{},url:{}", permit, url.getURL());
        return permit;
    }

    @Override
    public void visit(Page page) {
        DataRequest request = new DataRequest();
        request.setSourceType(sourceType);
        request.setPayload(page);
        dataEngine.process(request);
    }
}