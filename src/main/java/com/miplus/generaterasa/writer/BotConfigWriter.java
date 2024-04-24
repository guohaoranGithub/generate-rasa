package com.miplus.generaterasa.writer;

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
        String nluContent = this.getNluContent(botConfig);
        String nluPath = projectDirectory + "/data/" + "nlu.yml";
        this.write(nluContent, nluPath);

        //写入responses文件
        String responsesContent = this.getResponsesContent(botConfig);
        String responsesPath = projectDirectory + "/data/" + "responses.yml";
        this.write(responsesContent, responsesPath);

        //写入stories文件
        String storiesContent = this.getStoriesContent(botConfig);
        String storiesPath = projectDirectory + "/data/" + "stories.yml";
        this.write(storiesContent, storiesPath);

        //写入rules文件
        String rulesContent = this.getRulesContent(botConfig);
        String rulesPath = projectDirectory + "/data/" + "rules.yml";
        this.write(rulesContent, rulesPath);

        //写入domain文件
        String domainContent = this.getDomainContent(botConfig);
        String domainPath = projectDirectory + "/domain.yml";
        this.write(domainContent, domainPath);
    }

    public abstract String getNluContent(BotConfig config);

    public abstract String getResponsesContent(BotConfig config);

    public abstract String getStoriesContent(BotConfig config);

    public abstract String getRulesContent(BotConfig config);

    public abstract String getDomainContent(BotConfig config);

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
