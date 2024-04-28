package com.miplus.generaterasa.service;

import com.miplus.generaterasa.param.BindFAQParam;
import com.miplus.generaterasa.vo.FAQVo;
import com.miplus.generaterasa.writer.BotConfigWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 业务相关
 */
@Service
public class FAQService extends BotService {

    @Autowired
    private BotConfigWriter botConfigWriter;

    /**
     * 给机器人绑定FAQ数据
     *
     * @param param
     */
    public void bindData(BindFAQParam param) {
        //查数据库......
        List<FAQVo> faqVos = Arrays.asList(
                new FAQVo(Collections.singletonList("应聘ACME校园招聘职位的注意事项?"),
                        "1、登在校园招聘板块内的职位信息才适用于应届毕业生招聘，" +
                                "请所有的应届毕业生去校园招聘的版块寻找您感兴趣的职位。" +
                                "2、列出的每个职位的要求是该职位的最低要求，" +
                                "为了保证您应聘的成功率，希望您严格按照职位的要求考虑您的选择。" +
                                "3、提交成功后，在招聘结束前，您将不能修改或再次提交简历，因此，请于仔细确认填写信息后提交简历。"),
                new FAQVo(Collections.singletonList("校园招聘录取的应届生主要工作地点在哪里?"),
                        "招聘信息中包含各职位的工作地点内容，请参考各职位内容的详细介绍。"),
                new FAQVo(Arrays.asList("各阶段审核说明", "审核说明"),
                        "1、简历审核：应聘者需要通过ACME网站，填写并提交个人简历，" +
                                "ACME的招聘专员将对收取的简历进行认真的审查和筛选。" +
                                "了解应聘者的情况，并筛选出符合职位要求的简历，同时确认简历记载内容是否属实。" +
                                "2、笔试审核：ACME技术类测试主要针对应聘者的专业技能进行检查和评价。" +
                                "3、面试审核：经过实施评价应聘者基本素质的第一阶段面试和评价专业知识的第二阶段面试，" +
                                "对应聘者是否符合ACME人才理念以及应聘者的工作能力做出客观的综合评价，从而决定是否录用该应聘者。"));

        //写入nlu文件
        List<Map<String, Object>> newData = new ArrayList<>();
        for (int i = 0; i < faqVos.size(); i++) {
            FAQVo faqVo = faqVos.get(i);
            // 添加新的意图和样本数据
            LinkedHashMap<String, Object> newIntentData = new LinkedHashMap<>();
            List<String> problem = faqVo.getProblem();
            newIntentData.put("intent", "faq/problem");
            newIntentData.put("examples", problem);
            newData.add(newIntentData);
        }
        botConfigWriter.appendToNLUFile(param.getBotPath(), newData);
    }
}
