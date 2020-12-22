package com.tiny.core.common.snowflake;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amin
 */
@Configuration
@ConfigurationProperties(prefix = "framework.id-generator.snowflake")
public class IDGeneratorConfig {

    public static  final  String STRATEGY_DEFAULT = "53bits";

    public static  final  String STRATEGY_STANDARD = "standard";

    public static  final  String WORKER_ID = "workerId";

    public static  final  String BIZ_ID = "bizId";

    public static  final  String SNOWFLAKE_STRATEGY = "snowflake_strategy";

    /**
     * 机器编号
     */
    private int workerId = 0;

    /**
     * 业务编号
     */
    private int bizId = 0;

    /**
     * 默认的生成策略
     */
    private String strategy = STRATEGY_DEFAULT;


    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public int getBizId() {
        return bizId;
    }

    public void setBizId(int bizId) {
        this.bizId = bizId;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
}
