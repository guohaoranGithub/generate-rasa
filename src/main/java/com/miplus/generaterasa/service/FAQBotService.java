package com.miplus.generaterasa.service;

import com.miplus.generaterasa.config.BotConfig;
import com.miplus.generaterasa.config.NluConfig;
import com.miplus.generaterasa.model.BaseModel;
import com.miplus.generaterasa.model.FAQModel;
import com.miplus.generaterasa.utils.FileReader;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class FAQBotService extends CreateBotService {


    @Override
    public BotConfig voToConfig(BaseModel vo) {
        FAQModel faqVo = (FAQModel) vo; //向下转型
        BotConfig config = new BotConfig();
        config.setBotType(faqVo.getBotType());
        config.setProjectDirectory(vo.getPath());
        List<String> questions = ((FAQModel) vo).getQuestion();
        //todo 设置测试后数据，正式环境需要关闭，从数据库里拿
        FileReader fileReader = new FileReader();
        questions = fileReader.readLinesFromFile("src/main/resources/data/test/faq_nlu.txt");
        // todo...
        NluConfig nluConfig = new NluConfig();
        LinkedHashMap<String, List<String>> intentMap = nluConfig.getIntentMap();
        intentMap.put("faq",questions);
        nluConfig.setIntentMap(intentMap);
        config.setNluConfig(nluConfig);
        return config;
    }
}
