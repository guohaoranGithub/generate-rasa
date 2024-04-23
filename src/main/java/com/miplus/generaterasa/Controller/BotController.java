package com.miplus.generaterasa.Controller;

import com.miplus.generaterasa.service.FAQBotService;
import com.miplus.generaterasa.model.FAQModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bot")
public class BotController {
    @Autowired
    private FAQBotService faqBotService;


    /**
     * 创建FAQ/闲聊机器人
     *
     * @param faqModel
     * @return
     */
    @PostMapping("/createFAQBot")
    public String createFAQBot(@RequestBody @Validated FAQModel faqModel) {
        String path = faqBotService.createBot(faqModel);
        return "The robot project has been successfully created. Please go to the " + path + " directory to view it.";
    }


}
