package com.miplus.generaterasa.config;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Setter
@Getter
public class ResponsesConfig {

    LinkedHashMap<String, String> utterMap; //key: utter_intent, value: text

    public LinkedHashMap<String, String> getUtterMap() {
        if (null == this.utterMap) {
            this.utterMap = new LinkedHashMap<>();
        }
        return utterMap;
    }
}
