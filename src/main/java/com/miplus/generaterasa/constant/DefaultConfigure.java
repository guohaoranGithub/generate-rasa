package com.miplus.generaterasa.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 默认的一些配置
 */
public class DefaultConfigure {
    public static Map<String, String> INTENTS = null;

    static {
        INTENTS = new LinkedHashMap<>();
        INTENTS.put("goodbye", "src/main/resources/data/nlu/goodbye_intent.txt");
        INTENTS.put("greet", "src/main/resources/data/nlu/greet_intent.txt");
    }

    public static Map<String, String> FAQ_RESPONSES_UTTER = null;

    static {
        FAQ_RESPONSES_UTTER = new LinkedHashMap<>();
        FAQ_RESPONSES_UTTER.put("utter_greet", "您好，我是FAQ机器人");
        FAQ_RESPONSES_UTTER.put("utter_goodbye", "再见！");
        FAQ_RESPONSES_UTTER.put("utter_default", "我不明白您的话");
    }


}
