package com.miplus.generaterasa.writer;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * domain配置信息写入类
 */
@Component
public class DomainConfigWriter extends BotConfigWriter {
    /**
     * 加入新的domain项
     *
     * @param filePath
     * @param newData
     */
    public void appendToDomainFile(String filePath, Map<String, Object> newData) {
        filePath = filePath + "/domain.yml";
        // 读取现有的 nlu.yml 文件内容
        List<String> lines = this.readFile(filePath);
        // 将新的数据添加到现有数据中
        this.insertNewData(lines, newData);
        // 将更新后的数据写回文件
        this.write(filePath, lines);
    }

    /**
     * 添加新的domain配置项
     *
     * @param lines
     * @param newData
     */
    private void insertNewData(List<String> lines, Map<String, Object> newData) {
        //添加intents
        List<String> intents = (List<String>) newData.get("intents");
        // 找到 intents 字段所在的行
        int intentsLineIndex = -1;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith("intents:")) {
                intentsLineIndex = i;
                break;
            }
        }
        if (intentsLineIndex != -1) {
            // 找到 intents 字段最后一个元素的行
            int lastSampleLineIndex = -1;
            for (int i = intentsLineIndex + 1; i < lines.size(); i++) {
                String line = lines.get(i);
                if (!line.startsWith("  - ")) {
                    break;
                } else {
                    lastSampleLineIndex = i;
                }
            }
            // 拼接新intents
            if (lastSampleLineIndex != -1) {
                int num = lastSampleLineIndex;
                for (String intent : intents) {
                    if (lines.contains("  - " + intent)) {
                        continue;
                    }
                    num++;
                    lines.add(num, "  - " + intent);
                }
            }
        }
        //添加actions
        List<String> actions = (List<String>) newData.get("actions");
        // 找到 actions 字段所在的行
        int actionsLineIndex = -1;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith("actions:")) {
                actionsLineIndex = i;
                break;
            }
        }
        if (actionsLineIndex != -1) {
            // 找到 actions 字段最后一个元素的行
            int lastSampleLineIndex = -1;
            for (int i = actionsLineIndex + 1; i < lines.size(); i++) {
                String line = lines.get(i);
                if (!line.startsWith("  - ")) {
                    break;
                } else {
                    lastSampleLineIndex = i;
                }
            }
            // 拼接新intents
            if (lastSampleLineIndex != -1) {
                int num = lastSampleLineIndex;
                for (String action : actions) {
                    if (lines.contains("  - " + action)) {
                        continue;
                    }
                    num++;
                    lines.add(num, "  - " + action);
                }
            }
        }
    }
}
