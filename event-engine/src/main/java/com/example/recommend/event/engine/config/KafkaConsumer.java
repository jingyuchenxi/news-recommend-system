package com.example.recommend.event.engine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author:jycx
 * @date: 2024/12/3 21:04
 * @description:Kafka Consumer
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "news_topic", groupId = "news_consumer_group")
    public void consumeMessage(String message) {
        log.info("收到来自topic:{}-groupId:{}的消息,message:{}", "news_topic", "news_consumer_group", message);
    }
}