package com.tiny.core.redis.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Amin
 */
@Getter
@Setter
@ConfigurationProperties(TinyRedisProperties.PREFIX)
public class TinyRedisProperties {
    /**
     * 前缀
     */
    public static final String PREFIX = "tiny.nosql.redis";
    /**
     * 是否开启Lettuce
     */
    private Boolean enable = true;
}
