package com.tiny.core.config;


import com.tiny.core.common.snowflake.IDGenerator;
import com.tiny.core.common.snowflake.IDGeneratorConfig;
import com.tiny.core.tool.bean.ApplicationContextUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Amin
 */
@Configuration
@ConfigurationProperties(prefix = "tiny")
@ConfigurationPropertiesScan("${tiny.base-packages}")//替换为启动类根目录
@Import({IDGeneratorConfig.class})
public class DependenceAutoConfiguration {

    private String basePackages;

    @Bean
    public IDGenerator idGenerator(IDGeneratorConfig config) {
        String strategy = config.getStrategy();
        if (IDGeneratorConfig.STRATEGY_DEFAULT.equals(strategy)) {
            return IDGenerator.snowflake53bits(config.getWorkerId());
        } else if (IDGeneratorConfig.STRATEGY_STANDARD.equals(strategy)) {
            return IDGenerator.snowflake(config.getWorkerId(), config.getBizId());
        }
        return null;
    }

    @Bean
    public ApplicationContextUtils applicationContextUtils(ApplicationContext context){
        ApplicationContextUtils contextUtils =  new ApplicationContextUtils();
        contextUtils.setApplicationContext(context);
        return contextUtils;
    }


    public String getBasePackages() {
        return basePackages;
    }

    public void setBasePackages(String basePackages) {
        this.basePackages = basePackages;
    }
}
