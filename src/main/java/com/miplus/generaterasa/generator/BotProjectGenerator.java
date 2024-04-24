package com.miplus.generaterasa.generator;

import com.miplus.generaterasa.utils.DirectoryStructurePrinter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 生成Rasa项目
 */
@Component
public class BotProjectGenerator {
    /**
     * 创建rasa项目
     *
     * @param path path为绝对路径
     * @return
     */
    public String generateBotProject(String path) {
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
        System.out.println("====================================Successfully created robot project directory====================================");
        //打印项目结构
        DirectoryStructurePrinter.printDirectoryStructure(projectDir);
        return path;
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
