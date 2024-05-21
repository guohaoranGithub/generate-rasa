package com.miplus.generaterasa.generator;

import com.miplus.generaterasa.config.*;
import com.miplus.generaterasa.param.CreateBotParam;
import com.miplus.generaterasa.utils.BotManager;
import com.miplus.generaterasa.utils.DirectoryStructurePrinter;
import com.miplus.generaterasa.writer.BotConfigWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 生成Rasa项目
 */
@Component
public class BotProjectGenerator {
    @Autowired
    private BotConfigWriter botConfigWriter;

    /**
     * 创建rasa项目
     *
     * @param
     * @return
     */
    public String generateBotProject(CreateBotParam param) {
        String path = param.getBotPath();
        //初始化项目
        this.createProjectStructure(path);
        //初始化配置
        BotConfig defaultConfig = this.getDefaultConfig();
        defaultConfig.setProjectDirectory(path);
        defaultConfig.setBotName(param.getBotId());
        botConfigWriter.writeDefaultBotConfig(defaultConfig);
        Boolean train = param.getTrain();
        Boolean run = param.getRun();
        if(train) {
            BotManager.trainRasaModel(path);
        }
        if(run) {
            BotManager.runDockerCompose(path,path+ "/docker-compose.yml");
            BotManager.checkContainerStatus(param.getBotId() + "_rasa_service");
            BotManager.checkContainerStatus(param.getBotId() + "_action_server");
        }
        return path;
    }

    private BotConfig getDefaultConfig() {
        BotConfig config = new BotConfig();
        //domain配置
        DomainConfig domainConfig = new DomainConfig();
        domainConfig.setSessionConfig(new SessionConfig());
        config.setDomainConfig(domainConfig);
        //nlu配置
        NluConfig nluConfig = new NluConfig();
        LinkedHashMap<String, List<String>> intentMap = nluConfig.getIntentMap();
        nluConfig.setIntentMap(intentMap);
        config.setNluConfig(nluConfig);
        //rule配置
        RulesConfig rulesConfig = new RulesConfig();
        LinkedHashMap<String, String> stepsMap = rulesConfig.getStepsMap();
        stepsMap.put("nlu_fallback", "action_default_fallback");
        config.setRulesConfig(rulesConfig);
        //故事配置
        StoriesConfig storiesConfig = new StoriesConfig();
        config.setStoriesConfig(storiesConfig);
        return config;
    }

    private void createProjectStructure(String path) {
        // 创建项目目录
        File projectDir = new File(path);
        projectDir.mkdirs();
        // 创建data目录
        String dataDirPath = path + "/data/";
        File dataDir = new File(dataDirPath);
        dataDir.mkdirs();
        //创建nlu文件
        String nluFile = dataDirPath + "nlu.yml";
        createFile(nluFile);
        //创建responses文件
        String responsesFile = dataDirPath + "responses.yml";
        createFile(responsesFile);
        //创建rules文件
        String rulesFile = dataDirPath + "rules.yml";
        createFile(rulesFile);
        //创建stories文件
        String storiesFile = dataDirPath + "stories.yml";
        createFile(storiesFile);
        // 创建models目录
        String modelsDirPath = path + "/models/";
        File modelsDir = new File(modelsDirPath);
        modelsDir.mkdir();

        // 创建config文件
        String configFile = path + "/config.yml";
        createFile(configFile);

        // 创建domain文件
        String domainFile = path + "/domain.yml";
        createFile(domainFile);

        // 创建credentials文件
        String credentialsFile = path + "/credentials.yml";
        createFile(credentialsFile);

        // 创建endpoints文件
        String endpointsFile = path + "/endpoints.yml";
        createFile(endpointsFile);

        // 创建actions目录
        String actionsDirPath = path + "/actions/";
        File actionsDir = new File(actionsDirPath);
        actionsDir.mkdir();
        //创建actions文件
        String actionsFile = actionsDirPath + "actions.py";
        createFile(actionsFile);
        String initFile = actionsDirPath + "__init__.py";
        createFile(initFile);
        //创建docker-compose.yml
        String dockerComposeFile = path + "/docker-compose.yml";
        createFile(dockerComposeFile);
        System.out.println("====================================Successfully created robot project directory====================================");
        //打印项目结构
        DirectoryStructurePrinter.printDirectoryStructure(projectDir);
    }

    public static void createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
                // 写入文件内容
                FileWriter writer = new FileWriter(file);
                writer.write(""); // 可以写入初始内容
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
