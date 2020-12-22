package com.tiny.core.common.message;


/**
 * @author amin
 */
public abstract class ApplicationMessage {

    /**
     * 消息码
     */
    private int code;

    /**
     * 消息类型
     */
    private MessageType type;

    /**
     * 消息内容
     */
    private String message;

    protected ApplicationMessage(int code, String message, MessageType type) {
        this.code = code;
        this.message = message;
        this.type = type;
    }

    protected void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public MessageType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
