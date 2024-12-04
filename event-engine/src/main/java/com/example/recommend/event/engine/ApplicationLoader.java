package com.example.recommend.event.engine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.example.recommend"})
@MapperScan(basePackages = "com.example.recommend.persistent.mapper")
@EnableScheduling
@EnableAsync
public class ApplicationLoader {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationLoader.class, args);
    }
}