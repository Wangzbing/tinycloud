package com.tiny.core.common.message;


import com.tiny.core.common.util.StringUtils;

public class SystemMessage extends ApplicationMessage {

    public static final int UNKNOWN_ERROR_CODE = 10001;
    public static final String UNKNOWN_ERROR_10001_MESSAGE = "Server unknown error";

    public static final int CALL_TIMEOUT_CODE = 10002;
    public static final String CALL_TIMEOUT_10002_MESSAGE = "System call timeout";

    public static SystemMessage unknownError(String message) {
        return new SystemMessage(UNKNOWN_ERROR_CODE,
                StringUtils.isEmpty(message) ? UNKNOWN_ERROR_10001_MESSAGE : message , MessageType.SYSTEM);
    }

    public static SystemMessage callTimeOut(String message) {
        return new SystemMessage(CALL_TIMEOUT_CODE,
                StringUtils.isEmpty(message) ? CALL_TIMEOUT_10002_MESSAGE : message , MessageType.SYSTEM);
    }

    private SystemMessage(int code, String message, MessageType type) {
        super(code, message, type);
    }
}
