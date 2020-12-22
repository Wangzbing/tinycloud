package com.tiny.core.common.exception;


import com.tiny.core.common.message.ServiceMessage;


/**
 * @author Amin
 */
public class DuplicateValueException extends BusinessException {
    public DuplicateValueException() {
        super(ServiceMessage.duplicateValue(null));
    }
    public DuplicateValueException(String message) {
        super(ServiceMessage.duplicateValue(message));
    }
}
