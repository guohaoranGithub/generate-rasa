package com.miplus.generaterasa.model;

import com.miplus.generaterasa.Enum.BotTypeEnum;
import lombok.Data;

@Data
public class BaseModel {

    /**
     * 机器人类型
     */
    private BotTypeEnum botType;

    /**
     * 项目根路径
     */
    private String path;
}
