package com.tiny.core.common.exception;


import com.tiny.core.common.message.ApplicationMessage;

/**
 * @description: excpetion
 * @author: zlf
 * @Date: 2019-07-11
 */
public abstract class BusinessException extends RuntimeException{

    protected final int code;
    protected final ApplicationMessage applicationMessage;
    protected String message;

    public BusinessException(ApplicationMessage applicationMessage){
        this.message = applicationMessage.getMessage();
        this.code = applicationMessage.getCode();
        this.applicationMessage = applicationMessage;
    }

    public BusinessException(ApplicationMessage applicationMessage, Throwable cause) {
        super(cause);
        this.message = applicationMessage.getMessage();
        this.code = applicationMessage.getCode();
        this.applicationMessage = applicationMessage;
    }

    public BusinessException(ApplicationMessage applicationMessage, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(applicationMessage.getMessage(),cause, enableSuppression, writableStackTrace);
        this.message = applicationMessage.getMessage();
        this.code = applicationMessage.getCode();
        this.applicationMessage = applicationMessage;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ApplicationMessage getApplicationMessage() {
        return applicationMessage;
    }
}
