package com.miplus.generaterasa.service;

import com.miplus.generaterasa.Generator.BotConfigWriter;
import com.miplus.generaterasa.Generator.BotProjectGenerator;
import com.miplus.generaterasa.config.BotConfig;
import com.miplus.generaterasa.utils.SpringBeanUtil;
import com.miplus.generaterasa.model.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建rasa机器人项目
 */
@Service
public abstract class CreateBotService {
    @Autowired
    private BotProjectGenerator botProjectGenerator;

    //把数据库的数据对象解析成机器人配置类
    public abstract BotConfig voToConfig(BaseModel vo);

    public final String createBot(BaseModel model) {
        //创建项目目录和文件
        botProjectGenerator.generateBotProject(model);
        try {
            //解析vo
            BotConfig botConfig = this.voToConfig(model);
            //写入rasa配置
            BotConfigWriter writer = (BotConfigWriter)SpringBeanUtil.getBean(model.getBotType().getWriter());
            writer.writeBotConfig(botConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model.getPath();
    }

}
