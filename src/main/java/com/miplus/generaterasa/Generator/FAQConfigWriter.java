package com.miplus.generaterasa.Generator;

import com.miplus.generaterasa.config.BotConfig;
import com.miplus.generaterasa.config.NluConfig;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FAQConfigWriter extends BotConfigWriter{
    @Override
    public String getNluContent(BotConfig config) {
        NluConfig nluConfig = config.getNluConfig();
        HashMap<String, List<String>> intentMap = nluConfig.getIntentMap();

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("version: \"3.0\"\n");
        resultBuilder.append("nlu:\n");

        for (Map.Entry<String, List<String>> entry : intentMap.entrySet()) {
            String intent = entry.getKey();
            List<String> examples = entry.getValue();

            resultBuilder.append("  - intent: \"").append(intent).append("\"\n");
            resultBuilder.append("    examples: |\n");

            for (String example : examples) {
                resultBuilder.append("      - ").append(example).append("\n");
            }
        }

        return resultBuilder.toString();
    }
}
