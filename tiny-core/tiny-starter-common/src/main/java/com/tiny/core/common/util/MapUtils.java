package com.tiny.core.common.util;

import java.util.Map;

/**
 * @author EUN
 * @date 2019/3/26 15:53
 * @version 1.0
 */
public class MapUtils {

    public static boolean isNotEmpty(Map<?, ?> map) {
        return  org.apache.commons.collections4.MapUtils.isNotEmpty(map);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return  org.apache.commons.collections4.MapUtils.isEmpty(map);
    }
}
