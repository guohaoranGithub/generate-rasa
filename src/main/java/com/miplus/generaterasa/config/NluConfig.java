package com.miplus.generaterasa.config;

import com.miplus.generaterasa.utils.FileReader;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * nlu包括：
 * intent（意图）
 * examples（样本列表）
 */
@Setter
@Getter
public class NluConfig {

    public LinkedHashMap<String, List<String>> getIntentMap() {
        if (null == this.intentMap) {
            this.intentMap = new LinkedHashMap<>();
            //初始化默认配置
            FileReader fileReader = new FileReader();
            List<String> goodbyes = fileReader
                    .readLinesFromFile("src/main/resources/data/nlu/goodbye_intent.txt");
            intentMap.put("goodbye", goodbyes);
            List<String> greets = fileReader
                    .readLinesFromFile("src/main/resources/data/nlu/greet_intent.txt");
            intentMap.put("greet", greets);
        }
        return intentMap;
    }

    LinkedHashMap<String, List<String>> intentMap; //key: intent, value: examples

}
