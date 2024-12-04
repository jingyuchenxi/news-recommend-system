package com.example.recommend.event.engine.engine.parser.json;

import com.example.recommend.event.engine.dto.DataResult;
import com.example.recommend.event.engine.engine.parser.DataParser;
import com.example.recommend.event.engine.engine.parser.ParseCmd;
import com.example.recommend.event.engine.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:jycx
 * @date: 2024/12/4 10:37
 * @description:JSON类数据源解析(一般是API类型的接口or实时用户产生的数据)
 */
@Slf4j
public abstract class AbstractJsonParser implements DataParser<String, DataResult> {

    protected abstract DataResult doParse(String payload);

    @Override
    public DataResult parse(ParseCmd<String> cmd) {
        DataResult data = doParse(cmd.getParseInfo());
        log.info("[AbstractJsonParser][parse] finish, parseInfo:{}, data:{}", cmd.getParseInfo(), data);
        return data;
    }

    public static void main(String[] args) {
        String st = "{\"title\":\"股市资讯\",\"keywords\":[\"股市\",\"资讯\",\"波动\"],\"url\":\"https://www.baidu.com/\",\"content\":\"XXX等多只股票股票正在快速拉升\",\"author\":\"金融资讯网\",\"datePublished\":\"2024-12-03 12:01:05\"}";
        DataResult dataResult = JacksonUtil.fromJson(st, DataResult.class);
        System.out.println();
    }
}