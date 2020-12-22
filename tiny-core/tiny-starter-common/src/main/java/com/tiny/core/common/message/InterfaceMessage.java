package com.tiny.core.common.message;


import com.tiny.core.common.util.StringUtils;

/**
 * @author Amin
 */
public class InterfaceMessage extends ApplicationMessage {

    public static final int ILLEGAL_SIGNATURE_CODE = 10101;
    public static final String ILLEGAL_SIGNATURE_10101_MESSAGE = " Illegal signature ";

    public static final int INVALID_PARAMETER_CODE = 10102;
    public static final String INVALID_PARAMETER_10102_MESSAGE = "Request params not valid ";

    public static final int RESUBMIT_CODE = 10103;
    public static final String RESUBMIT_10103_MESSAGE = " Resubmit data";

    public static InterfaceMessage illegalSignature(String message) {
        return new InterfaceMessage(ILLEGAL_SIGNATURE_CODE,
                StringUtils.isEmpty(message) ? ILLEGAL_SIGNATURE_10101_MESSAGE : message , MessageType.API);
    }

    public static InterfaceMessage invalidParameter(String message) {
        return new InterfaceMessage(INVALID_PARAMETER_CODE,
                StringUtils.isEmpty(message) ? INVALID_PARAMETER_10102_MESSAGE : message , MessageType.API);
    }

    public static InterfaceMessage resubmit(String message) {
        return new InterfaceMessage(RESUBMIT_CODE,
                StringUtils.isEmpty(message) ? RESUBMIT_10103_MESSAGE : message , MessageType.API);
    }

    private InterfaceMessage(int code, String message, MessageType type) {
        super(code, message, type);
    }
}
