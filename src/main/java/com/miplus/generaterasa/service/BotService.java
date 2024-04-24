package com.miplus.generaterasa.service;

import com.miplus.generaterasa.config.BotConfig;
import com.miplus.generaterasa.utils.SpringBeanUtil;
import com.miplus.generaterasa.writer.BotConfigWriter;
import org.springframework.stereotype.Service;

/**
 * 创建rasa机器人项目
 */
@Service
public class BotService {
    /**
     * 写入文件
     *
     * @param config
     * @return
     */
    public void writeToBot(BotConfig config) {
        try {
            //写入rasa配置
            BotConfigWriter writer = (BotConfigWriter) SpringBeanUtil.getBean(config.getBotType().getWriter());
            writer.writeBotConfig(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
