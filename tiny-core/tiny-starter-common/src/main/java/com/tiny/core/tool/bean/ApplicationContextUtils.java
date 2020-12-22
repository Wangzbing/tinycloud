package com.tiny.core.tool.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ApplicationContextUtils.context = context;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static ConfigurableApplicationContext getConfigurableApplicationContext(){
       return  (ConfigurableApplicationContext)context;
    }

    public static <T> T getBean(Class<T> clazz){
        return context.getBean(clazz);
    }

}
