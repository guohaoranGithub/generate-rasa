package com.miplus.generaterasa.config;

import com.miplus.generaterasa.constant.DefaultConfigure;
import com.miplus.generaterasa.utils.FileReader;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * nlu包括：
 * intent（意图）
 * examples（样本列表）
 */
@Setter
@Getter
public class NluConfig {

    public LinkedHashMap<String, List<String>> getIntentMap() {
        //初始化默认配置
        if (null == this.intentMap) {
            this.intentMap = new LinkedHashMap<>();
            FileReader fileReader = new FileReader();
            Map<String, String> intents = DefaultConfigure.INTENTS;
            for (Map.Entry<String, String> entry : intents.entrySet()) {
                String intent = entry.getKey();
                String path = entry.getValue();
                intentMap.put(intent, fileReader
                        .readLinesFromFile(path));
            }
        }
        return intentMap;
    }

    LinkedHashMap<String, List<String>> intentMap; //key: intent, value: examples

}
