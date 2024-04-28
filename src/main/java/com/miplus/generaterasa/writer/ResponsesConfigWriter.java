package com.miplus.generaterasa.writer;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * responses配置写入类
 */
@Component
public class ResponsesConfigWriter extends BotConfigWriter {
    /**
     * 加入新的response项
     *
     * @param filePath
     * @param newData
     */
    public void appendToResponseFile(String filePath, List<Map<String, String>> newData) {
        filePath = filePath + "/data/responses.yml";
        // 读取现有的 nlu.yml 文件内容
        List<String> lines = this.readFile(filePath);
        // 将新的数据添加到现有数据中
        this.insertNewData(lines, newData);
        // 将更新后的数据写回文件
        this.write(filePath, lines);
    }

    private void insertNewData(List<String> lines, List<Map<String, String>> newData) {
        // 直接在末尾行加
        for (Map<String, String> map : newData) {
            String utter = map.get("utter");
            String text = map.get("text");
            if(lines.contains("  " + utter + ":")) {
                continue;
            }
            lines.add("  " + utter + ":");
            lines.add("    - text: " + text);
        }
    }
}
