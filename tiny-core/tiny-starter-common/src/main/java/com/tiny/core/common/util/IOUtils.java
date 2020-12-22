package com.tiny.core.common.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author
 * @date
 * @todo:TODO version:1.0
 */
public final class IOUtils {

    public static final String DEFAUL_FILE_SUFFIXES = ".txt";

    public static boolean isRemoteAddress(String path) {
        Pattern pattern = Pattern.compile(
                "(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*");
        Matcher matcher = pattern.matcher(path);

        return matcher.find();
    }

    public static Path createPath(String... segments) {

        if (org.apache.commons.lang3.StringUtils.isNoneEmpty(segments)) {

            Path path = Paths.get(segments[0]);
            for (int i = 1; i < segments.length; i++) {
                path = path.resolve(segments[i]);
            }

            return path;
        }

        return Paths.get(".");
    }


    public static Path createFileName(Path parent, String fileSuffixes, String name) {

        if (parent == null) {
            throw new NullPointerException("The parent must be not null...");
        }

        if (org.apache.commons.lang3.StringUtils.isEmpty(fileSuffixes))
            fileSuffixes = DEFAUL_FILE_SUFFIXES;

        if (org.apache.commons.lang3.StringUtils.isNoneEmpty(name)) {
            return parent.resolve(name + fileSuffixes);
        }

        return parent;
    }

    public static String generateFileNameByDateTime() {
        return String.valueOf(LocalDateTime.now().getLong(ChronoField.MICRO_OF_DAY));
    }


    /**
     * 
     */
    private IOUtils() {}

}
