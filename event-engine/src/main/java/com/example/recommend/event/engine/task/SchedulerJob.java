package com.example.recommend.event.engine.task;

import com.example.recommend.event.engine.crawler.Crawler;
import com.example.recommend.event.engine.dto.DataRequest;
import com.example.recommend.event.engine.engine.DataEngine;
import com.example.recommend.event.engine.engine.DataEngineConfig;
import com.example.recommend.event.engine.enums.SourceTypeEnum;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerJob {

    @Autowired
    private DataEngine dataEngine;

    public void init() {
        // 任务可以从这里初始化
    }

    @Scheduled(cron = "* 0/1 * * * ?")
    public void soHuJob() {
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder("/Users/aurora/Desktop/code/recommend/news-recommend-system/folder");
        config.setMaxDepthOfCrawling(5);
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        robotstxtConfig.setUserAgentName("agentName");
        robotstxtConfig.setEnabled(false);
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        try {
            CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
            controller.addSeed(DataEngineConfig.SOURCE_URI_CONFIG.get(SourceTypeEnum.SO_HU));
            // 爬虫工厂模式
            controller.start(() -> new Crawler(SourceTypeEnum.SO_HU, dataEngine), 1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //@Scheduled(cron = "0/20 * * * * ?")
    public void peoplesDailyJob() {
        DataRequest request = new DataRequest();
        request.setSourceType(SourceTypeEnum.PEOPLES_DAILY);
        request.setPayload(null);
        dataEngine.process(request);
    }

    //@Scheduled(cron = "0/10 * * * * ?")
    public void stockJob() {
        DataRequest request = new DataRequest();
        request.setSourceType(SourceTypeEnum.STOCK);
        request.setPayload(null);
        dataEngine.process(request);
    }
}