package com.miplus.generaterasa.Generator;

import com.miplus.generaterasa.utils.DirectoryStructurePrinter;
import com.miplus.generaterasa.model.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.apache.tomcat.util.http.fileupload.FileUtils.deleteDirectory;

/**
 * 生成Rasa项目
 */
@Component
public class BotProjectGenerator {
    @Autowired
    private ResourceLoader resourceLoader;

    public void generateBotProject(BaseModel vo) {
        // 获取resources目录的路径
        try {
            String resourcesPath = resourceLoader.getResource("classpath:").getFile().getPath();

            // 创建项目目录
            String projectDirPath = resourcesPath + "/" + vo.getBotType().getValue() + "/";
            File projectDir = new File(projectDirPath);
            if (projectDir.exists()) {
                deleteDirectory(projectDir);
            }
            projectDir.mkdir();
            vo.setPath(projectDirPath);
            // 创建data目录
            String dataDirPath = projectDirPath + "data/";
            File dataDir = new File(dataDirPath);
            dataDir.mkdir();
            //创建nlu文件
            String nluFile = dataDirPath + "nlu.yml";
            createFile(nluFile);
            //创建rules文件
            String rulesFile = dataDirPath + "rules.yml";
            createFile(rulesFile);
            //创建stories文件
            String storiesFile = dataDirPath + "stories.yml";
            createFile(storiesFile);
            // 创建models目录
            String modelsDirPath = projectDirPath + "models/";
            File modelsDir = new File(modelsDirPath);
            modelsDir.mkdir();

            // 创建config文件
            String configFile = projectDirPath + "config.yml";
            createFile(configFile);

            // 创建domain文件
            String domainFile = projectDirPath + "domain.yml";
            createFile(domainFile);

            // 创建credentials文件
            String credentialsFile = projectDirPath + "credentials.yml";
            createFile(credentialsFile);

            // 创建endpoints文件
            String endpointsFile = projectDirPath + "endpoints.yml";
            createFile(endpointsFile);

            // 创建actions目录
            String actionsDirPath = projectDirPath + "actions/";
            File actionsDir = new File(actionsDirPath);
            actionsDir.mkdir();
            //创建actions文件
            String actionsFile = actionsDirPath + "actions.py";
            createFile(actionsFile);
            //打印项目结构
            DirectoryStructurePrinter.printDirectoryStructure(projectDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
