package com.miplus.generaterasa.config;

import com.miplus.generaterasa.Enum.BotTypeEnum;
import lombok.Data;

/**
 * 转换成rasa配置文件时使用
 */
@Data
public class BotConfig {
    /**
     * 类型
     */
    private BotTypeEnum botType;
    /**
     * 机器人项目的目录路径
     */
    private String projectDirectory;
    /**
     * nlu配置对象
     */
    private NluConfig nluConfig;

}
