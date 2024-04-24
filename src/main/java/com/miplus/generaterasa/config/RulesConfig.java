package com.miplus.generaterasa.config;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Setter
@Getter
public class RulesConfig {
    LinkedHashMap<String, String> stepsMap;

    public LinkedHashMap<String, String> getStepsMap() {
        if(null == stepsMap) {
            stepsMap = new LinkedHashMap<>();
        }
        return stepsMap;
    }
}
