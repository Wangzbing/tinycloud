package props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Amin
 */
@Getter
@Setter
@ConfigurationProperties(MongoDBRedisProperties.PREFIX)
public class MongoDBRedisProperties {
    /**
     * 前缀
     */
    public static final String PREFIX = "tiny.nosql.mongo";
    /**
     * 是否开启Lettuce
     */
    private Boolean enable = true;
}
