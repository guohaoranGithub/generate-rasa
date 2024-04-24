package com.miplus.generaterasa.writer;

import com.miplus.generaterasa.config.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class FAQConfigWriter extends BotConfigWriter {
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

            resultBuilder.append("  - intent: ").append(intent).append("\n");
            resultBuilder.append("    examples: |\n");

            for (String example : examples) {
                resultBuilder.append("      - ").append(example).append("\n");
            }
        }

        return resultBuilder.toString();
    }

    @Override
    public String getResponsesContent(BotConfig config) {
        ResponsesConfig responsesConfig = config.getResponsesConfig();
        LinkedHashMap<String, String> utterMap = responsesConfig.getUtterMap();

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("version: \"3.0\"\n");
        resultBuilder.append("responses:\n");

        for (Map.Entry<String, String> entry : utterMap.entrySet()) {
            String utter = entry.getKey();
            String text = entry.getValue();

            resultBuilder.append("  ").append(utter).append(": ").append("\n");
            resultBuilder.append("    - text: ").append(text).append("\n");
        }

        return resultBuilder.toString();
    }

    @Override
    public String getStoriesContent(BotConfig config) {
        StoriesConfig storiesConfig = config.getStoriesConfig();
        LinkedHashMap<String, String> stepsMap = storiesConfig.getStepsMap();

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("version: \"3.0\"\n");
        resultBuilder.append("stories:\n");

        for (Map.Entry<String, String> entry : stepsMap.entrySet()) {
            String intent = entry.getKey();
            String action = entry.getValue();
            resultBuilder.append("  - story: ").append(intent).append("\n");
            resultBuilder.append("    steps:\n");
            resultBuilder.append("      - intent: ").append(intent).append("\n");
            resultBuilder.append("      - action: ").append(action).append("\n");
        }

        return resultBuilder.toString();
    }

    @Override
    public String getRulesContent(BotConfig config) {
        RulesConfig rulesConfig = config.getRulesConfig();
        LinkedHashMap<String, String> stepsMap = rulesConfig.getStepsMap();

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("version: \"3.0\"\n");
        resultBuilder.append("rules:\n");

        for (Map.Entry<String, String> entry : stepsMap.entrySet()) {
            String intent = entry.getKey();
            String action = entry.getValue();
            resultBuilder.append("  - rule: respond to ").append(intent).append("\n");
            resultBuilder.append("    steps:\n");
            resultBuilder.append("      - intent: ").append(intent).append("\n");
            resultBuilder.append("      - action: ").append(action).append("\n");
        }

        return resultBuilder.toString();
    }

    @Override
    public String getDomainContent(BotConfig config) {
        DomainConfig domainConfig = config.getDomainConfig();

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("version: \"3.0\"\n");
        resultBuilder.append("session_config:\n");
        resultBuilder.append("  session_expiration_time: ")
                .append(domainConfig.getSessionConfig().getSessionExpirationTime()).append("\n");
        resultBuilder.append("  carry_over_slots_to_new_session: ")
                .append(domainConfig.getSessionConfig().getCarryOverSlotsToNewSession()).append("\n");
        List<String> intents = domainConfig.getIntents();
        resultBuilder.append("intents:\n");
        for (String intent : intents) {
            resultBuilder.append("  - ").append(intent).append("\n");
        }
        resultBuilder.append("responses:\n");
        LinkedHashMap<String, String> responsesMap = domainConfig.getResponsesMap();
        for (Map.Entry<String, String> entry : responsesMap.entrySet()) {
            String utter = entry.getKey();
            String text = entry.getValue();
            resultBuilder.append("  ").append(utter).append(":\n");
            resultBuilder.append("    - text: ").append(text).append("\n");
        }
        resultBuilder.append("actions:\n");
        List<String> actions = domainConfig.getActions();
        for (String action : actions) {
            resultBuilder.append("  - ").append(action).append("\n");
        }
        return resultBuilder.toString();
    }
}
