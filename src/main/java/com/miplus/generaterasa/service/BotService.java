package com.miplus.generaterasa.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 把数据转成rasa配置,调用writer拼接文件内容等
 */
@Service
public class BotService {

    /**
     * 生成意图不重复意图名
     *
     * @param question
     * @return
     */
    public String generateIntentName(String question) {
        try {
            // 创建 MessageDigest 对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 对问题内容进行哈希运算
            md.update(question.getBytes());
            // 获取哈希值
            byte[] digest = md.digest();
            // 将哈希值转换为正数
            BigInteger bigInt = new BigInteger(1, digest);
            // 将哈希值转换为字符串作为意图名称
            return bigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
