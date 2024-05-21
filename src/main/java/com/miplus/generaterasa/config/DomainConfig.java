package com.miplus.generaterasa.config;

import com.miplus.generaterasa.constant.DefaultConfigure;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class DomainConfig {
    /**
     * session_config
     */
    private SessionConfig sessionConfig;

    public List<String> getIntents() {
        if (null == intents || intents.isEmpty()) {
            //初始化默认配置
            intents = new ArrayList<>();
            Map<String, List<String>> intentsMap = DefaultConfigure.INTENTS;
            intents.addAll(intentsMap.keySet());
        }
        return intents;
    }

    /**
     * 意图
     */
    private List<String> intents;

    public LinkedHashMap<String, String> getResponsesMap() {
        if (null == responsesMap) {
            responsesMap = new LinkedHashMap<>();
            //初始化默认配置
            Map<String, String> faqResponsesUtter = DefaultConfigure.RESPONSES_UTTER;
            for (Map.Entry<String, String> entry : faqResponsesUtter.entrySet()) {
                String utter = entry.getKey();
                String text = entry.getValue();
                responsesMap.put(utter, text);
            }
        }
        return responsesMap;
    }
    /**
     * 响应
     */
    private LinkedHashMap<String, String> responsesMap;

    public List<String> getActions() {
        if (null == actions || actions.isEmpty()) {
            //初始化默认配置
            actions = new ArrayList<>();
            Map<String, String> faqResponsesUtter = DefaultConfigure.RESPONSES_UTTER;
            actions.addAll(faqResponsesUtter.keySet());
        }
        return actions;
    }

    /**
     * 动作
     */
    private List<String> actions;
}
