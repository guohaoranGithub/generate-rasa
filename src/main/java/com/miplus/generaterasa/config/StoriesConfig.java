package com.miplus.generaterasa.config;

import com.miplus.generaterasa.constant.DefaultConfigure;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class StoriesConfig {
    LinkedHashMap<String, String> stepsMap;

    public LinkedHashMap<String, String> getStepsMap() {
        if (null == this.stepsMap) {
            stepsMap = new LinkedHashMap<>();
            //初始化默认配置
            Map<String, List<String>> intents = DefaultConfigure.INTENTS;
            for (Map.Entry<String, List<String>> entry : intents.entrySet()) {
                String intent = entry.getKey();
                stepsMap.put(intent, "utter_" + intent);
            }

        }
        return stepsMap;
    }
}
