package com.miplus.generaterasa.config;

import lombok.Data;

/**
 * 转换成rasa配置文件时使用
 */
@Data
public class BotConfig {
    /**
     * 机器人项目的目录路径
     */
    private String projectDirectory;
    /**
     * nlu配置对象
     */
    private NluConfig nluConfig;
    /**
     * responses配置对象
     */
    private ResponsesConfig responsesConfig;
    /**
     * stories配置对象
     */
    private StoriesConfig storiesConfig;
    /**
     * rules配置对象
     */
    private RulesConfig rulesConfig;
    /**
     * domain配置对象
     */
    private DomainConfig domainConfig;

    /**
     * 机器人名称
     */
    private String botName;

}
