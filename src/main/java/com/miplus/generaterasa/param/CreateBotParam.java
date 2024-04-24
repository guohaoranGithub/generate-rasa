package com.miplus.generaterasa.param;

import lombok.Data;

/**
 * 创建机器人使用的参数
 * 参数包括：
 * 1. 机器人id（项目名bot-机器人id）
 * 2. 路径（放置此项目的绝对路径）
 */
@Data
public class CreateBotParam {
    /** 机器人id */
    private String botId;
    /** 路径 */
    private String path;
}
