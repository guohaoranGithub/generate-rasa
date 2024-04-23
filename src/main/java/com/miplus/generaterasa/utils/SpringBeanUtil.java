package com.miplus.generaterasa.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class SpringBeanUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        T t = applicationContext != null ? applicationContext.getBean(clazz) : null;
        Assert.notNull(t, "当前Bean为空，请重新选择！");
        return t;
    }

    public static Object getBean(String clazzName) {
        return applicationContext.getBean(clazzName);
    }
}
