//package com.miplus.generaterasa.constant;
//
///**
// * 问题类型枚举
// */
//public enum BotTypeEnum {
//    FAQ(1, "faq"), //FAQ
//    CHITCHAT(2, "chitchat"); //闲聊
//
//    private final int code;
//    private final String value;
//
//    BotTypeEnum(int code, String value) {
//        this.value = value;
//        this.code = code;
//    }
//
//    public String getValue() {
//        return value;
//    }
//
//
//    public static String getValueByCode(int code) {
//        for (BotTypeEnum botTypeEnum : BotTypeEnum.values()) {
//            if (botTypeEnum.code == code) {
//                return botTypeEnum.value;
//            }
//        }
//        return null;
//    }
//
//    public static BotTypeEnum getByCode(int code) {
//        for (BotTypeEnum botTypeEnum : BotTypeEnum.values()) {
//            if (botTypeEnum.code == code) {
//                return botTypeEnum;
//            }
//        }
//        return null;
//    }
//}
