package com.tiny.core.common.message;


import com.tiny.core.common.util.StringUtils;

/**
 * @author Amin
 */
public class ServiceMessage extends ApplicationMessage {

    public static final int DUPLICATE_VALUE_CODE = 10201;
    public static final String DUPLICATE_VALUE_10201_MESSAGE = " Duplicate value ";

    public static final int EMPTY_VALUE_CODE = 10202;
    public static final String EMPTY_VALUE_10202_MESSAGE = "Empty value";

    public static final int ILLEGAL_VALUE_CODE = 10203;
    public static final String ILLEGAL_VALUE_10203_MESSAGE = " Illegal value";

    public static ServiceMessage duplicateValue(String message) {
        return new ServiceMessage(DUPLICATE_VALUE_CODE,
                StringUtils.isEmpty(message) ? DUPLICATE_VALUE_10201_MESSAGE : message , MessageType.SERVICE);
    }

    public static ServiceMessage emptyValue(String message) {
        return new ServiceMessage(EMPTY_VALUE_CODE,
                StringUtils.isEmpty(message) ? EMPTY_VALUE_10202_MESSAGE : message , MessageType.SERVICE);
    }

    public static ServiceMessage illegalValue(String message) {
        return new ServiceMessage(ILLEGAL_VALUE_CODE,
                StringUtils.isEmpty(message) ? ILLEGAL_VALUE_10203_MESSAGE : message , MessageType.SERVICE);
    }

    private ServiceMessage(int code, String message, MessageType type) {
        super(code, message, type);
    }
}
