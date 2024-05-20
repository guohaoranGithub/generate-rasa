package com.miplus.generaterasa.param;

import lombok.Data;

@Data
public class RasaParam {
    /**
     * 是否训练
     */
    private Boolean train = false;
    /**
     * 是否运行
     */
    private Boolean run = false;
    /**
     * 机器人id
     */
    private String botId;
    /**
     * 路径
     */
    private String path;
    /**
     * 全路径
     */
    private String botPath;

    public String getBotPath() {
        return this.path + "/" + this.botId;
    }
}
