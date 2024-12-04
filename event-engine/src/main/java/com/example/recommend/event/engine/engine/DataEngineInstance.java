package com.example.recommend.event.engine.engine;

import com.example.recommend.event.engine.dto.DataRequest;
import com.example.recommend.event.engine.dto.DataResult;
import com.example.recommend.event.engine.engine.fetcher.FetchCmd;
import com.example.recommend.event.engine.engine.fetcher.FetchEngine;
import com.example.recommend.event.engine.engine.parser.ParseCmd;
import com.example.recommend.event.engine.engine.parser.ParseEngine;
import com.example.recommend.event.engine.engine.store.StoreEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:jycx
 * @date: 2024/12/4 12:49
 * @description:数据引擎
 */
@Component("dataEngine")
public class DataEngineInstance implements DataEngine {
    @Autowired
    private FetchEngine fetchEngine;

    @Autowired
    private ParseEngine parseEngine;

    @Autowired
    private StoreEngine storeEngine;

    @Override
    public void process(DataRequest cmd) {
        // 查询
        FetchCmd fetchCmd = new FetchCmd();
        fetchCmd.setSourceType(cmd.getSourceType());
        fetchCmd.setFetchInfo(cmd.getPayload());
        Object fetch = fetchEngine.fetch(fetchCmd);

        // 解析
        ParseCmd parseCmd = new ParseCmd();
        parseCmd.setSourceType(cmd.getSourceType());
        parseCmd.setParseInfo(fetch);
        DataResult data = parseEngine.parse(parseCmd);

        // 存储
        storeEngine.store(cmd.getSourceType(), data);
    }
}