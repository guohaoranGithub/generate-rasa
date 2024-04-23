package com.miplus.generaterasa.model;

import com.miplus.generaterasa.Enum.BotTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * FAQ
 */
@Data
public class FAQModel extends BaseModel {
    /**
     * 机器人类型
     */
    private BotTypeEnum botType = BotTypeEnum.FAQ;
    /**
     * 问题
     */
    private List<String> question;


    /**
     * 答案
     */
    private List<String> answer;

}
