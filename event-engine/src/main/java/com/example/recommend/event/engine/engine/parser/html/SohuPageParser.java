package com.example.recommend.event.engine.engine.parser.html;

import com.example.recommend.event.engine.annotation.ParseType;
import com.example.recommend.event.engine.dto.DataResult;
import com.example.recommend.event.engine.enums.ParseTypeEnum;
import com.google.common.collect.Lists;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.ParseData;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author:jycx
 * @date: 2024/12/1 18:33
 * @description:搜狐解析
 */
@ParseType(type = ParseTypeEnum.SO_HU)
public class SohuPageParser extends AbstractPageParser {

    @Override
    protected DataResult doParse(Page page) {
        String originUrl = page.getWebURL().getURL();
        ParseData parseData = page.getParseData();
        if (parseData instanceof HtmlParseData) {
            Document doc = Jsoup.parse(((HtmlParseData) parseData).getHtml());
            // 标题
            String title = doc.title();

            // meta
            Elements meta = doc.select("meta");

            // 关键词
            List<String> keywords = Lists.newArrayList();

            // 作者
            String author = null;

            // 发布时间
            String datePublished = null;
            for (Element m : meta) {
                String name = m.attr("name");
                String itemprop = m.attr("itemprop");
                String content = m.attr("content");
                if (StringUtils.equalsIgnoreCase(itemprop, "datePublished")) {
                    datePublished = m.attr("content");
                }
                if (StringUtils.equalsIgnoreCase(name, "keywords")) {
                    keywords = Arrays.asList(StringUtils.split(content, ","));
                }
                if (StringUtils.equalsIgnoreCase(name, "author")) {
                    author = m.attr("content");
                }
            }

            // 正文内容
            String content = Optional.ofNullable(doc.getElementById("mp-editor")).map(Element::text).orElse(StringUtils.EMPTY);

            // 提前过滤无效页面
            if (StringUtils.isEmpty(content)) {
                return null;
            }
            DataResult dataResult = new DataResult();
            dataResult.setTitle(title);
            dataResult.setKeywords(keywords);
            dataResult.setUrl(originUrl);
            dataResult.setContent(content);
            dataResult.setAuthor(author);
            dataResult.setDatePublished(datePublished);
            return dataResult;
        }
        return null;
    }
}