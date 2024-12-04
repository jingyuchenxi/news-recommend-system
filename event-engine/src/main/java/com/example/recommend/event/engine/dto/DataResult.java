package com.example.recommend.event.engine.dto;

import com.example.recommend.event.engine.util.ListStrDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DataResult {
    /**
     * 标题
     */
    private String title;
    /**
     * 关键词
     */
    @JsonDeserialize(using = ListStrDeserializer.class)
    private List<String> keywords;
    /**
     * 链接
     */
    private String url;
    /**
     * 内容
     */
    private String content;
    /**
     * 作者
     */
    private String author;
    /**
     * 发布时间
     */
    private String datePublished;
}