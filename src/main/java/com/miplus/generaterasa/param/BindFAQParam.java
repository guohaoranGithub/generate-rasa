package com.miplus.generaterasa.param;

import lombok.Data;

import java.util.List;

/**
 * 绑定知识库参数
 */
@Data
public class BindFAQParam extends RasaParam{
    /** 是否全部绑定 */
    private Integer isBindAll;
    /** 类目 */
    private List<String> category;
}
