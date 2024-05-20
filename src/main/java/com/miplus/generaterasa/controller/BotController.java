package com.miplus.generaterasa.controller;

import com.miplus.generaterasa.generator.BotProjectGenerator;
import com.miplus.generaterasa.param.BindFAQParam;
import com.miplus.generaterasa.param.CreateBotParam;
import com.miplus.generaterasa.service.ChitchatService;
import com.miplus.generaterasa.service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bot")
public class BotController {
    @Autowired
    private BotProjectGenerator generator;
    @Autowired
    private FAQService faqService;
    @Autowired
    private ChitchatService chitchatService;

    /**
     * 创建机器人项目
     * 初始化项目结构，基础文件
     *
     * @param param
     * @return
     */
    @PostMapping("/createBot")
    public String createBot(@RequestBody CreateBotParam param) {
        String result = generator.generateBotProject(param);
        return "The robot project has been successfully created. Please go to the " + result + " directory to view it.";
    }

    /**
     * 绑定FAQ
     *
     * @param param
     */
    @PostMapping("/bindFAQ")
    public void bindFAQ(@RequestBody BindFAQParam param) {
        faqService.bindData(param);
    }

    /**
     * 绑定闲聊
     *
     * @param param
     */
    @PostMapping("/bindChitchat")
    public void bindChitchat(@RequestBody BindFAQParam param) {
        chitchatService.bindData(param);
    }
}
