package com.example.recommend.event.engine.etl;

import com.example.recommend.event.engine.dto.DataResult;
import com.example.recommend.event.engine.util.JacksonUtil;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.connector.jdbc.JdbcStatementBuilder;
import org.apache.flink.connector.jdbc.internal.options.JdbcConnectorOptions;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Objects;
import java.util.Properties;

/**
 * @author:jycx
 * @date: 2024/12/3 14:01
 * @description:ETL清洗任务
 */
@Slf4j
public class FlinkRtETL4Kafka {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // init source
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>("news_topic", new SimpleStringSchema(), properties);

        // add stream source
        DataStream<String> kafkaStream = env.addSource(consumer);

        // stream etl
        DataStream<DataResult> etlStream = kafkaStream.map(new DataCleaningFunction()).filter(Objects::nonNull);

        // init sink
        JdbcConnectionOptions connectionOptions = JdbcConnectorOptions.builder()
                .setDBUrl("jdbc:mysql://localhost:3306/material")
                .setTableName("news_info")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("root")
                .setPassword("12345678")
                .build();

        JdbcExecutionOptions executionOptions = JdbcExecutionOptions.builder().withBatchIntervalMs(200).build();
        etlStream.addSink(JdbcSink.sink("insert into news_info(title, keywords, url, author, date_published, content) values(?, ?, ?, ?, ?, ?)",
                (JdbcStatementBuilder<DataResult>) (ps, r) -> {
                    ps.setString(1, r.getTitle());
                    ps.setString(2, Joiner.on(",").join(r.getKeywords()));
                    ps.setString(3, r.getUrl());
                    ps.setString(4, StringUtils.defaultString(r.getAuthor()));
                    ps.setString(5, r.getDatePublished());
                    ps.setString(6, r.getContent());
                }, executionOptions, connectionOptions));

        // execute job
        env.execute("FlinkRtETL4Kafka");
    }

    public static class DataCleaningFunction implements MapFunction<String, DataResult> {

        @Override
        public DataResult map(String msg) throws Exception {
            try {
                log.warn("[DataCleaningFunction] mapper receive:{}", msg);
                DataResult result = JacksonUtil.fromJson(msg, DataResult.class);
                // 数据清洗(eg: 过滤无标题material or datePublished过旧的material)
                if (StringUtils.isEmpty(result.getTitle())) {
                    return null;
                }
                return result;
            } catch (Exception e) {
                log.warn("[DataCleaningFunction] mapper error, msg:{}", msg, e);
            }
            return null;
        }
    }
}