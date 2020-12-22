package com.tiny.core.common.util;

import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 包名将在1.2中替换为util
 * @author
 * @date
 * @todo:TODO
 * version:1.0
 */
public final class CollectionUtils {
    
    public static boolean isEmpty(Collection<?> collection) {
        return org.apache.commons.collections4.CollectionUtils.isEmpty(collection);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return org.apache.commons.collections4.CollectionUtils.isNotEmpty(collection);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return org.apache.commons.collections4.MapUtils.isEmpty(map);

    }

    public static List arrayToList(@Nullable Object source) {
        return org.springframework.util.CollectionUtils.arrayToList(source);
    }

    private CollectionUtils() {
        super();
    }
    
}
