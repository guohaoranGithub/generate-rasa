package com.miplus.generaterasa.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认的一些配置
 */
public class DefaultConfigure {
    public static Map<String, List<String>> INTENTS = null;

    static {
        INTENTS = new LinkedHashMap<>();
        INTENTS.put("goodbye", Arrays.asList("拜拜", "再见", "拜", "退出", "结束"));
        INTENTS.put("greet", Arrays.asList("你好", "您好", "hello", "hi", "喂", "在吗"));
    }

    public static Map<String, String> RESPONSES_UTTER = null;

    static {
        RESPONSES_UTTER = new LinkedHashMap<>();
        RESPONSES_UTTER.put("utter_goodbye", "再见！");
        RESPONSES_UTTER.put("utter_greet", "您好，我是基于rasa的机器人，您可以向我提问问题");
        RESPONSES_UTTER.put("utter_default", "我不明白您的话");
        RESPONSES_UTTER.put("utter_ask_rephrase", "抱歉没听懂，请您重新表述");
    }


}
