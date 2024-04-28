package com.miplus.generaterasa.writer;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 写入rules配置信息类
 */
@Component
public class RulesConfigWriter extends BotConfigWriter {
    /**
     * 加入新的rules项
     *
     * @param filePath
     * @param newData
     */
    public void appendToRulesFile(String filePath, List<Map<String, Object>> newData) {
        filePath = filePath + "/data/rules.yml";
        // 读取现有的 nlu.yml 文件内容
        List<String> lines = this.readFile(filePath);
        // 将新的数据添加到现有数据中
        this.insertNewData(lines, newData);
        // 将更新后的数据写回文件
        this.write(filePath, lines);
    }

    /**
     * 插入一些新的规则
     * @param lines
     * @param newData
     */
    private void insertNewData(List<String> lines, List<Map<String, Object>> newData) {
        // 直接在末尾行加,如果之前加过的就不加了
        for (Map<String, Object> map : newData) {
            String rule = (String)map.get("rule");
            if(lines.contains("- rule: "+rule)) {
                continue;
            }
            lines.add("- rule: " + rule);
            lines.add("  steps:");
            Map<String, String> stepMap = (LinkedHashMap<String, String>) map.get("steps");
            String intent = stepMap.get("intent");
            String action = stepMap.get("action");
            lines.add("  - intent: " + intent);
            lines.add("  - action: " + action);
        }
    }
}
