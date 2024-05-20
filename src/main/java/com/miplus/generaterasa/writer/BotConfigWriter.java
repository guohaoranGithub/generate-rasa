package com.miplus.generaterasa.writer;

import com.miplus.generaterasa.config.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * 负责机器人配置文件的写入
 */
@Component
public class BotConfigWriter {

    /**
     * 读取文件内容，把每行数据顺序加到list的元素里
     *
     * @param filePath
     * @return
     */
    public List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }



    /**
     * 将list数据顺序写到文件里
     *
     * @param filePath
     * @param lines
     */
    public void write(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入配置文件
     *
     * @param content
     * @param filePath
     */
    public void write(String content, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入默认的rasa配置信息
     *
     * @param botConfig
     */
    public void writeDefaultBotConfig(BotConfig botConfig) {
        //项目根路径
        String projectDirectory = botConfig.getProjectDirectory();
        //写入config文件
        String configContent = this.getConfigContent(botConfig);
        String configPath = projectDirectory + "/config.yml";
        this.write(configContent, configPath);

        //写入credentials文件
        String credentialsContent = this.getCredentialsContent(botConfig);
        String credentialsPath = projectDirectory + "/credentials.yml";
        this.write(credentialsContent, credentialsPath);

        //写入domain文件
        String domainContent = this.getDomainContent(botConfig);
        String domainPath = projectDirectory + "/domain.yml";
        this.write(domainContent, domainPath);

        //写入endpoints文件
        String endpointsContent = this.getEndpointsContent(botConfig);
        String endpointsPath = projectDirectory + "/endpoints.yml";
        this.write(endpointsContent, endpointsPath);

        //写入nul文件
        String nluContent = this.getNluContent(botConfig);
        String nluPath = projectDirectory + "/data/" + "nlu.yml";
        this.write(nluContent, nluPath);

        //写入rules文件
        String rulesContent = this.getRulesContent(botConfig);
        String rulesPath = projectDirectory + "/data/" + "rules.yml";
        this.write(rulesContent, rulesPath);

        //写入stories文件
        String storiesContent = this.getStoriesContent(botConfig);
        String storiesPath = projectDirectory + "/data/" + "stories.yml";
        this.write(storiesContent, storiesPath);

        //写入responses文件
        String responsesContent = this.getResponsesContent(botConfig);
        String responsesPath = projectDirectory + "/data/" + "responses.yml";
        this.write(responsesContent, responsesPath);

        //启动文件
        String dockerContent = this.getDockerContent(botConfig);
        String dockerPath = projectDirectory + "/docker-compose.yml";
        this.write(dockerContent, dockerPath);
    }

    /**
     * 默认的responses配置
     *
     * @param botConfig
     * @return
     */
    private String getResponsesContent(BotConfig botConfig) {
        return "version: \"3.0\"\n" +
                "#responses:";
    }

    /**
     * docker
     * @return
     */
    private String getDockerContent(BotConfig config) {

        return "version: '3.0'\n"+
                "services:\n"+
                "  "+config.getBotName()+"_rasa_service:\n"+
                "    image: rasa/rasa:3.0.13\n"+
                "    ports:\n"+
                "      - 5005:5005\n"+
                "    volumes:\n"+
                "      - ./:/app\n"+
                "    command:\n"+
                "      - run\n"+
                "      - --enable-api\n"+
                "    depends_on:\n"+
                "      - action_server\n\n"+
                "  "+config.getBotName()+"_action_server:\n"+
                "    image: rasa/rasa:3.0.13\n"+
                "    ports:\n"+
                "      - 5055:5055\n"+
                "    volumes:\n"+
                "      - ./:/app\n"+
                "    command:\n"+
                "      - run\n"+
                "      - actions"
                ;
    }

    /**
     * 默认的nlu配置
     *
     * @param config
     * @return
     */
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

    /**
     * 默认的stories配置
     *
     * @param config
     * @return
     */
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

    /**
     * 默认的rules配置
     *
     * @param config
     * @return
     */
    public String getRulesContent(BotConfig config) {
        RulesConfig rulesConfig = config.getRulesConfig();
        LinkedHashMap<String, String> stepsMap = rulesConfig.getStepsMap();

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("version: \"3.0\"\n");
        resultBuilder.append("rules:\n");
        for (Map.Entry<String, String> entry : stepsMap.entrySet()) {
            String intent = entry.getKey();
            String action = entry.getValue();
            resultBuilder.append("- rule: respond to ").append(intent).append("\n");
            resultBuilder.append("  steps:\n");
            resultBuilder.append("  - intent: ").append(intent).append("\n");
            resultBuilder.append("  - action: ").append(action).append("\n");
        }

        return resultBuilder.toString();
    }

    /**
     * 默认的domain配置
     *
     * @param config
     * @return
     */
    public String getDomainContent(BotConfig config) {
        DomainConfig domainConfig = config.getDomainConfig();

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("version: \"3.0\"\n\n");
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

    /**
     * config 默认配置
     *
     * @param config
     * @return
     */
    public String getConfigContent(BotConfig config) {
        return "recipe: default.v1\n" +
                "language: zh\n\n" +
                "pipeline:\n" +
                "  - name: JiebaTokenizer\n" +
                "  - name: LanguageModelFeaturizer\n" +
                "    model_name: bert\n" +
                "    model_weights: bert-base-chinese\n" +
                "  - name: DIETClassifier\n" +
                "    epochs: 100\n" +
                "    tensorboard_log_directory: ./log\n" +
                "    learning_rate: 0.001\n" +
                "  - name: FallbackClassifier\n" +
                "    threshold: 0.3\n" +
                "    ambiguity_threshold: 0.1\n" +
                "  - name: ResponseSelector\n" +
                "    epochs: 100\n"+
                "    retrieval_intent: faq\n"+
                "  - name: ResponseSelector\n" +
                "    epochs: 100\n"+
                "    retrieval_intent: chitchat\n"+
                "policies:\n" +
                "  - name: MemoizationPolicy\n" +
                "  - name: TEDPolicy\n" +
                "    max_history: 5\n" +
                "    epochs: 100\n" +
                "  - name: RulePolicy\n" +
                "    core_fallback_threshold: 0.3\n" +
                "    core_fallback_action_name: \"action_default_fallback\"\n" +
                "    enable_fallback_prediction: True\n";
    }

    /**
     * 默认的endpoints配置
     *
     * @param config
     * @return
     */
    public String getEndpointsContent(BotConfig config) {
        return "# This file contains the different endpoints your bot can use.\n\n" +
                "# Server where the models are pulled from.\n" +
                "# https://rasa.com/docs/rasa/user-guide/running-the-server/#fetching-models-from-a-server/\n\n" +
                "#models:\n" +
                "#  url: http://my-server.com/models/default_core@latest\n" +
                "#  wait_time_between_pulls:  10   # [optional](default: 100)\n\n" +
                "# Server which runs your custom actions.\n" +
                "# https://rasa.com/docs/rasa/core/actions/#custom-actions/\n\n" +
                "action_endpoint:\n" +
                "  url: \"http://localhost:5055/webhook\"\n\n" +
                "# Tracker store which is used to store the conversations.\n" +
                "# By default the conversations are stored in memory.\n" +
                "# https://rasa.com/docs/rasa/api/tracker-stores/\n\n" +
                "#tracker_store:\n" +
                "#    type: redis\n" +
                "#    url: <host of the redis instance, e.g. localhost>\n" +
                "#    port: <port of your redis instance, usually 6379>\n" +
                "#    db: <number of your database within redis, e.g. 0>\n" +
                "#    password: <password used for authentication>\n\n" +
                "#tracker_store:\n" +
                "#    type: mongod\n" +
                "#    url: <url to your mongo instance, e.g. mongodb://localhost:27017>\n" +
                "#    db: <name of the db within your mongo instance, e.g. rasa>\n" +
                "#    username: <username used for authentication>\n" +
                "#    password: <password used for authentication>\n\n" +
                "# Event broker which all conversation events should be streamed to.\n" +
                "# https://rasa.com/docs/rasa/api/event-brokers/\n\n" +
                "#event_broker:\n" +
                "#  url: localhost\n" +
                "#  username: username\n" +
                "#  password: password\n" +
                "#  queue: queue\n";
    }

    /**
     * 默认的credentials配置
     *
     * @param config
     * @return
     */
    public String getCredentialsContent(BotConfig config) {
        return "# This file contains the credentials for the voice & chat platforms\n" +
                "# which your bot is using.\n" +
                "# https://rasa.com/docs/rasa/user-guide/messaging-and-voice-channels/\n\n" +
                "rest:\n" +
                "#  # you don't need to provide anything here - this channel doesn't\n" +
                "#  # require any credentials\n\n\n" +
                "#facebook:\n" +
                "#  verify: \"<verify>\"\n" +
                "#  secret: \"<your secret>\"\n" +
                "#  page-access-token: \"<your page access token>\"\n\n" +
                "#slack:\n" +
                "#  slack_token: \"<your slack token>\"\n" +
                "#  slack_channel: \"<the slack channel>\"\n\n" +
                "socketio:\n" +
                "  user_message_evt: user_uttered\n" +
                "  bot_message_evt: bot_uttered\n" +
                "  session_persistence: false\n\n" +
                "rasa:\n" +
                "  url: \"http://localhost:5002/api\"";
    }
}
