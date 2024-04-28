package com.miplus.generaterasa.vo;

import lombok.Data;

import java.util.List;

/**
 * todo 数据库faq对象，实际可能会有区别需要改动
 */
@Data
public class FAQVo {
    /**
     * 问题
     */
    private List<String> problem;
    /**
     * 答案
     */
    private String answer;

    public FAQVo(List<String> problem, String answer) {
        this.problem = problem;
        this.answer = answer;
    }
}
