package com.tiny.core.common.util;

import com.fasterxml.uuid.Generators;
import com.tiny.core.common.constant.StringConstant;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.SystemUtils;

import java.util.UUID;



/**
 * @author Amin
 */
public final class Utils {


    public static String getNoDashUUID() {
        return UUID.randomUUID().toString().replace(StringConstant.DASH, StringConstant.EMPTY);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static UUID getTimeBasedUUID() {
        return Generators.timeBasedGenerator().generate();
    }

    public static long nextLong(final long startInclusive, final long endExclusive) {
        return  RandomUtils.nextLong(startInclusive,endExclusive);
    }

    public static String getHostName() {
        return SystemUtils.getHostName();
    }
 
    private Utils() {}

}
