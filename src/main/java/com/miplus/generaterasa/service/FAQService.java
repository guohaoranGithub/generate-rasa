package com.miplus.generaterasa.service;

import com.miplus.generaterasa.param.BindFAQParam;
import com.miplus.generaterasa.utils.BotManager;
import com.miplus.generaterasa.vo.FAQVo;
import com.miplus.generaterasa.writer.DomainConfigWriter;
import com.miplus.generaterasa.writer.NluConfigWriter;
import com.miplus.generaterasa.writer.ResponsesConfigWriter;
import com.miplus.generaterasa.writer.RulesConfigWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 业务相关
 */
@Service
public class FAQService extends BotService {

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
        List<FAQVo> faqVos = Arrays.asList(
                new FAQVo(Collections.singletonList("应聘ACME校园招聘职位的注意事项?"),
                        "1、登在校园招聘板块内的职位信息才适用于应届毕业生招聘，" +
                                "请所有的应届毕业生去校园招聘的版块寻找您感兴趣的职位。" +
                                "2、列出的每个职位的要求是该职位的最低要求，" +
                                "为了保证您应聘的成功率，希望您严格按照职位的要求考虑您的选择。" +
                                "3、提交成功后，在招聘结束前，您将不能修改或再次提交简历，因此，请于仔细确认填写信息后提交简历。"),
                new FAQVo(Collections.singletonList("校园招聘录取的应届生主要工作地点在哪里?"),
                        "招聘信息中包含各职位的工作地点内容，请参考各职位内容的详细介绍。"),
                new FAQVo(Collections.singletonList("如何查询面试结果?"), "我们会通过邮件或电话的形式，通知您面试结果。"),
                new FAQVo(Arrays.asList("各阶段审核说明", "审核说明"),
                        "1、简历审核：应聘者需要通过ACME网站，填写并提交个人简历，" +
                                "ACME的招聘专员将对收取的简历进行认真的审查和筛选。" +
                                "了解应聘者的情况，并筛选出符合职位要求的简历，同时确认简历记载内容是否属实。" +
                                "2、笔试审核：ACME技术类测试主要针对应聘者的专业技能进行检查和评价。" +
                                "3、面试审核：经过实施评价应聘者基本素质的第一阶段面试和评价专业知识的第二阶段面试，" +
                                "对应聘者是否符合ACME人才理念以及应聘者的工作能力做出客观的综合评价，从而决定是否录用该应聘者。"));

        List<Map<String, Object>> newNluDataList = new ArrayList<>();
        List<Map<String, Object>> newResponsesDataList = new ArrayList<>();
        List<Map<String, Object>> newRulesDataList = new ArrayList<>();
        Map<String, Object> newDomainDataMap = new LinkedHashMap<>();
        for (int i = 0; i < faqVos.size(); i++) {
            FAQVo faqVo = faqVos.get(i);
            // 添加新的意图和样本数据
            LinkedHashMap<String, Object> newIntentData = new LinkedHashMap<>();
            List<String> problems = faqVo.getProblem();
            String problem = this.generateIntentName(problems.get(0));
            newIntentData.put("intent", "faq/" + problem);
            newIntentData.put("examples", problems);
            newNluDataList.add(newIntentData);
            //添加结果数据
            LinkedHashMap<String, Object> newResponsesDataMap = new LinkedHashMap<>();
            newResponsesDataMap.put("utter", "utter_faq/" + problem);
            newResponsesDataMap.put("text", Collections.singletonList(faqVo.getAnswer()));
            newResponsesDataList.add(newResponsesDataMap);
        }
        //写入nlu
        nluConfigWriter.appendToNLUFile(param.getBotPath(), newNluDataList);
        //写入responses
        responsesConfigWriter.appendToResponseFile(param.getBotPath(), newResponsesDataList);
        //写入rules
        //添加规则数据
        LinkedHashMap<String, Object> newRulesDataMap = new LinkedHashMap<>();
        newRulesDataMap.put("rule", "respond to FAQs");
        LinkedHashMap<String, String> stepsMap = new LinkedHashMap<>();
        stepsMap.put("intent", "faq");
        stepsMap.put("action", "utter_faq");
        newRulesDataMap.put("steps", stepsMap);
        newRulesDataList.add(newRulesDataMap);
        rulesConfigWriter.appendToRulesFile(param.getBotPath(), newRulesDataList);
        //添加domain数据
        newDomainDataMap.put("intents", Collections.singletonList("faq"));
        newDomainDataMap.put("actions", Collections.singletonList("respond_faq"));
        domainConfigWriter.appendToDomainFile(param.getBotPath(), newDomainDataMap);

        Boolean train = param.getTrain();
        Boolean run = param.getRun();
        if(train) {
            BotManager.trainRasaModel(param.getBotPath());
        }
        if(run) {
            BotManager.runDockerCompose(param.getBotPath()+ "/docker-compose.yml");
            BotManager.checkContainerStatus(param.getBotId() + "_rasa_service");
            BotManager.checkContainerStatus(param.getBotId() + "_action_server");
        }
    }
}
