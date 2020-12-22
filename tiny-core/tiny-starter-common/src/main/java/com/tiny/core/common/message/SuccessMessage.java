package com.tiny.core.common.message;

/**
 * @author Amin
 */
public class SuccessMessage extends ApplicationMessage {

    public static final int SUCCESS_CODE = 10000;

    public static SuccessMessage successful(){
        return new SuccessMessage(SUCCESS_CODE, "successful", MessageType.SYSTEM);
    }

    public static SuccessMessage successful(String message){
        return new SuccessMessage(SUCCESS_CODE, message, MessageType.SYSTEM);
    }

    private SuccessMessage(int code, String message, MessageType type) {
        super(code, message, type);
    }
}
