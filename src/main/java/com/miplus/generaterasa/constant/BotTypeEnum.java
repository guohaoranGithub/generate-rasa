package com.miplus.generaterasa.constant;

/**
 * 问题类型枚举
 */
public enum BotTypeEnum {
    FAQ(1, "faq","FAQConfigWriter"), //FAQ
    CHITCHAT(2, "chitchat", "chitchatWriter"); //闲聊

    private final int code;
    private final String value;
    private final String writer;

    BotTypeEnum(int code, String value, String writer) {
        this.value = value;
        this.code = code;
        this.writer = writer;
    }

    public String getValue() {
        return value;
    }

    public String getWriter() {
        return writer;
    }

    public static String getValueByCode(int code) {
        for (BotTypeEnum botTypeEnum : BotTypeEnum.values()) {
            if (botTypeEnum.code == code) {
                return botTypeEnum.value;
            }
        }
        return null;
    }

    public static BotTypeEnum getByCode(int code) {
        for (BotTypeEnum botTypeEnum : BotTypeEnum.values()) {
            if (botTypeEnum.code == code) {
                return botTypeEnum;
            }
        }
        return null;
    }
}
