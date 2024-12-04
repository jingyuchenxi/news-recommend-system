package com.example.recommend.event.engine.sheet;

import com.example.recommend.persistent.entity.NewsInfoPo;
import com.example.recommend.persistent.mapper.NewsInfoPoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sheet")
@Slf4j
public class ReportView {

    @Autowired
    private NewsInfoPoMapper newsInfoPoMapper;

    @GetMapping(value = "/selectById")
    public NewsInfoPo selectById(@RequestParam(name = "id") Long id) {
        NewsInfoPo newsInfoPo = newsInfoPoMapper.selectByPrimaryKey(id);
        log.info("[ReportView][selectById] id:{}, repo:{}", id, newsInfoPo);
        return newsInfoPo;
    }
}