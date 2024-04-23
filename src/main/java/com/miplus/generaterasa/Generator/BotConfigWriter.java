package com.miplus.generaterasa.Generator;

import com.miplus.generaterasa.config.BotConfig;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 负责机器人配置文件的写入
 */
@Component
public abstract class BotConfigWriter {

    public void writeBotConfig(BotConfig botConfig) {
        //项目根路径
        String projectDirectory = botConfig.getProjectDirectory();

        //写入nul文件
        String nluPath = projectDirectory + "/data/" + "nlu.yml";
        String nluContent = this.getNluContent(botConfig);
        this.write(nluContent, nluPath);
    }

    public abstract String getNluContent(BotConfig config);

    private void write(String content, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
