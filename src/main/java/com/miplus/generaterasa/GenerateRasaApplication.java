package com.miplus.generaterasa;

import com.miplus.generaterasa.Enum.BotTypeEnum;
import com.miplus.generaterasa.model.FAQModel;
import com.miplus.generaterasa.service.FAQBotService;
import com.miplus.generaterasa.utils.SpringBeanUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GenerateRasaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenerateRasaApplication.class, args);
        //todo test
        FAQBotService service = (FAQBotService)SpringBeanUtil.getBean("FAQBotService");
        FAQModel faqModel = new FAQModel();
        faqModel.setBotType(BotTypeEnum.FAQ);
        service.createBot(faqModel);
    }

}
