package com.tiny.core.common.exception;


import com.tiny.core.common.message.ServiceMessage;

/**
 * @author Amin
 */
public class PasswordException extends BusinessException{

    public PasswordException(ServiceMessage serviceMessage) {
        super(serviceMessage);
    }

}
