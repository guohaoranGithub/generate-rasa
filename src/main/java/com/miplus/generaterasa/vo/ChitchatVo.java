package com.miplus.generaterasa.vo;

import lombok.Data;

import java.util.List;

@Data
public class ChitchatVo {
    /**
     * 问题
     */
    private List<String> problem;
    /**
     * 答案
     */
    private List<String> answer;

    public ChitchatVo(List<String> problem, List<String> answer) {
        this.problem = problem;
        this.answer = answer;
    }
}
