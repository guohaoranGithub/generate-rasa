package com.miplus.generaterasa.writer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
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
    public void appendToResponseFile(String filePath, List<Map<String, Object>> newData) {
        filePath = filePath + "/data/responses.yml";
        // 读取现有的 nlu.yml 文件内容
        List<String> lines = this.readFile(filePath);
        // 将新的数据添加到现有数据中
        this.insertNewData(lines, newData);
        // 将更新后的数据写回文件
        this.write(filePath, lines);
    }

    private void insertNewData(List<String> lines, List<Map<String, Object>> newData) {
        for (int i = 0; i < lines.size(); i++) {
            String item = lines.get(i);
            if (item.trim().equals("#responses:")) {
                lines.set(i, "responses:");
            }
        }
        // 直接在末尾行加
        for (Map<String, Object> map : newData) {
            String utter = (String) map.get("utter");
            List<String> texts = (List<String>) map.get("text");
            if(lines.contains("  " + utter + ":")) {
                continue;
            }
            lines.add("  " + utter + ":");
            for (String text : texts) {
                lines.add("    - text: " + text);
            }
        }
    }
}
