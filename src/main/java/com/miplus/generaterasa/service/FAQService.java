package com.miplus.generaterasa.service;

import com.miplus.generaterasa.param.BindFAQParam;
import com.miplus.generaterasa.vo.FAQVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *业务相关
 */
@Service
public class FAQService extends BotService {
    /**
     * 给机器人绑定FAQ数据
     *
     * @param param
     */
    public void bindData(BindFAQParam param) {
        //查数据库......
        ArrayList<String> strings = new ArrayList<>();
        strings.add("可以申请几个职位?");
        strings.add("我能申请几个职位?");
        List<FAQVo> faqVos = Arrays.asList(
                new FAQVo("应聘ACME校园招聘职位的注意事项?", "1、登在校园招聘板块内的职位信息才适用于应届毕业生招聘，请所有的应届毕业生去校园招聘的版块寻找您感兴趣的职位。2、列出的每个职位的要求是该职位的最低要求，为了保证您应聘的成功率，希望您严格按照职位的要求考虑您的选择。3、提交成功后，在招聘结束前，您将不能修改或再次提交简历，因此，请于仔细确认填写信息后提交简历。", null),
                new FAQVo("校园招聘录取的应届生主要工作地点在哪里?", "招聘信息中包含各职位的工作地点内容，请参考各职位内容的详细介绍。", null),
                new FAQVo("最多申请几个职位?", "对于校园招聘，最多申请2个职位。", strings),
                new FAQVo("各阶段审核说明", "1、简历审核：应聘者需要通过ACME网站，填写并提交个人简历，ACME的招聘专员将对收取的简历进行认真的审查和筛选。了解应聘者的情况，并筛选出符合职位要求的简历，同时确认简历记载内容是否属实。2、笔试审核：ACME技术类测试主要针对应聘者的专业技能进行检查和评价。3、面试审核：经过实施评价应聘者基本素质的第一阶段面试和评价专业知识的第二阶段面试，对应聘者是否符合ACME人才理念以及应聘者的工作能力做出客观的综合评价，从而决定是否录用该应聘者。", null),
                new FAQVo("怎样参加笔试?", "通过简历审核的应聘者，我们将采用短信、e-mail、ACME公告栏以及电话通知的方式告知您", null),
                new FAQVo("笔试考试地点如何安排?", "笔试地点将根据您在简历中填写的学校所在城市进行统筹安排", null),
                new FAQVo("笔试只安排一次吗?我笔试当天没有参加，是否还有再次笔试的机会?", "校园招聘的大规模的笔试仅安排一次，请收到笔试通知的同学认真对待笔试机会。", null),
                new FAQVo("如果我没有收到笔试通知，但我很想进入ACME，能否直接进入考场参加考试?", "由于我们是按照严格的招聘流程筛选出的笔试名单，所以非常抱歉，对于没有收到笔试通知的同学，就不能参加本次校园招聘的笔试。", null),
                new FAQVo("面试什么时候开始?会提前多少天通知面试安排?", "不同的职位面试进度安排不同，除特殊安排外，笔试结束一周左右会安排面试。", null),
                new FAQVo("一般会安排几次面试?", "一般情况下，业务部门和人力资源部会同时或者分别安排一次面试。个别特殊职位需要2次及以上的面试。", null));

        //数据库对象转成config对象


        //写入rasa配置文件
        //this.写入
    }


}
