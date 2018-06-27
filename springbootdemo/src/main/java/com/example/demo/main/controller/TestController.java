package com.example.demo.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mysql")
@PropertySource("classpath:conf/test.yml")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

//    获取jdbc连接
    @Autowired
    JdbcTemplate jdbcTemplate;

//    获取配置文件自定义数据
    @Value("${test.properties1}")
    private String properties1;

    @Value("${test.properties2}")
    private String properties2;

    @Value("${test}")
    private String test;

    @RequestMapping("/query")
    public String query(){
        String sql = "select count(1) from customer";
        int count = jdbcTemplate.queryForObject(sql,Integer.class);
        return count+"";
    }

    @RequestMapping("/querylist")
    public List querylist(){
        String sql = "select * from customer";
        return jdbcTemplate.queryForList(sql);
    }

    @RequestMapping("/properties")
    public String getProperties(){
        return properties1+properties2;
    }

    @RequestMapping("/test")
    public String gettest(){
        return test;
    }

    @RequestMapping("/log")
    public String log(){
        logger.info("info");
        logger.error("error");
        logger.debug("debug");
        logger.warn("warn");
        return "true";
    }

}
