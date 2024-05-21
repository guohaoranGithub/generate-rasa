package com.miplus.generaterasa.service;

import com.miplus.generaterasa.param.BindFAQParam;
import com.miplus.generaterasa.utils.BotManager;
import com.miplus.generaterasa.vo.ChitchatVo;
import com.miplus.generaterasa.writer.DomainConfigWriter;
import com.miplus.generaterasa.writer.NluConfigWriter;
import com.miplus.generaterasa.writer.ResponsesConfigWriter;
import com.miplus.generaterasa.writer.RulesConfigWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChitchatService extends BotService {
    @Autowired
    private NluConfigWriter nluConfigWriter;
    @Autowired
    private ResponsesConfigWriter responsesConfigWriter;
    @Autowired
    private RulesConfigWriter rulesConfigWriter;
    @Autowired
    private DomainConfigWriter domainConfigWriter;

    /**
     * 给机器人绑定FAQ数据
     *
     * @param param
     */
    public void bindData(BindFAQParam param) {
        //查数据库......todo 未来可以把数据库数据通过参数传过来
        List<ChitchatVo> chitchatVos = Arrays.asList(new ChitchatVo(Arrays.asList("你的名字叫什么", "可以告诉你的名字吗?", "大家都叫你什么?"),
                        Arrays.asList("我是基于rasa的机器人", "我叫小白")),
                new ChitchatVo(Arrays.asList("你能做什么", "你的能力是什么", "我可以问你任何问题吗"),
                        Arrays.asList("我无所不知", "你可以向我提出问题")));

        List<Map<String, Object>> newNluDataList = new ArrayList<>();
        List<Map<String, Object>> newResponsesDataList = new ArrayList<>();
        List<Map<String, Object>> newRulesDataList = new ArrayList<>();
        Map<String, Object> newDomainDataMap = new LinkedHashMap<>();
        for (int i = 0; i < chitchatVos.size(); i++) {
            ChitchatVo vo = chitchatVos.get(i);
            // 添加新的意图和样本数据
            LinkedHashMap<String, Object> newIntentData = new LinkedHashMap<>();
            List<String> problems = vo.getProblem();
            String problem = this.generateIntentName(problems.get(0));
            newIntentData.put("intent", "chitchat/" + problem);
            newIntentData.put("examples", problems);
            newNluDataList.add(newIntentData);
            //添加结果数据
            LinkedHashMap<String, Object> newResponsesDataMap = new LinkedHashMap<>();
            newResponsesDataMap.put("utter", "utter_chitchat/" + problem);
            newResponsesDataMap.put("text", vo.getAnswer());
            newResponsesDataList.add(newResponsesDataMap);
        }
        //写入nlu
        nluConfigWriter.appendToNLUFile(param.getBotPath(), newNluDataList);
        //写入responses
        responsesConfigWriter.appendToResponseFile(param.getBotPath(), newResponsesDataList);
        //写入rules
        //添加规则数据
        LinkedHashMap<String, Object> newRulesDataMap = new LinkedHashMap<>();
        newRulesDataMap.put("rule", "respond to chitchat");
        LinkedHashMap<String, String> stepsMap = new LinkedHashMap<>();
        stepsMap.put("intent", "chitchat");
        stepsMap.put("action", "utter_chitchat");
        newRulesDataMap.put("steps", stepsMap);
        newRulesDataList.add(newRulesDataMap);
        rulesConfigWriter.appendToRulesFile(param.getBotPath(), newRulesDataList);
        //添加domain数据
        newDomainDataMap.put("intents", Collections.singletonList("chitchat"));
        newDomainDataMap.put("actions", Collections.singletonList("respond_chitchat"));
        domainConfigWriter.appendToDomainFile(param.getBotPath(), newDomainDataMap);

        Boolean run = param.getRun();
        if (run) {
            BotManager.runDockerCompose(param.getBotPath(), param.getBotPath() + "/docker-compose.yml");
            BotManager.checkContainerStatus(param.getBotId() + "_rasa_service");
            BotManager.checkContainerStatus(param.getBotId() + "_action_server");
        }
    }
}
