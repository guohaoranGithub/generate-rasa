package com.miplus.generaterasa.writer;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * nlu配置写入类
 */
@Component
public class NluConfigWriter extends BotConfigWriter {
    /**
     * 加入新的意图和样本
     *
     * @param filePath
     * @param newData
     */
    public void appendToNLUFile(String filePath, List<Map<String, Object>> newData) {
        filePath = filePath + "/data/nlu.yml";
        // 读取现有的 nlu.yml 文件内容
        List<String> lines = this.readFile(filePath);
        // 将新的意图和样本数据添加到现有数据中
        this.insertNewDataToNlu(lines, newData);
        // 将更新后的数据写回 nlu.yml 文件
        this.write(filePath, lines);
    }

    /**
     * 给nlu添加新的意图和样本数据
     *
     * @param lines
     * @param newData
     */
    private void insertNewDataToNlu(List<String> lines, List<Map<String, Object>> newData) {
        // 找到 examples 字段所在的行
        int lastExampleLineIndex = -1;
        for (int i = lines.size() - 1; i >= 0; i--) {
            String line = lines.get(i);
            if (line.trim().startsWith("examples:")) {
                lastExampleLineIndex = i;
                break;
            }
        }
        if (lastExampleLineIndex != -1) {
            // 找到 examples 字段的最后一个样本的行
            int lastSampleLineIndex = -1;
            for (int i = lastExampleLineIndex + 1; i < lines.size(); i++) {
                String line = lines.get(i);
                if (!line.startsWith("      - ")) {
                    break;
                } else {
                    lastSampleLineIndex = i;
                }
            }
            // 拼接新样本
            if (lastSampleLineIndex != -1) {
                int num = lastSampleLineIndex;
                for (Map<String, Object> map : newData) {
                    String intent = (String) map.get("intent");
                    List<String> examples = (List<String>) map.get("examples");
                    if (lines.contains("  - intent: " + intent)) {
                        continue;
                    }
                    num++;
                    lines.add(num, "  - intent: " + intent);
                    num++;
                    lines.add(num, "    examples: |");
                    for (String example : examples) {
                        num++;
                        lines.add(num, "      - " + example);
                    }
                }
            }
        }
    }
}
