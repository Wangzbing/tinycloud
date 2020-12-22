package com.tiny.core.common.exception;

import com.tiny.core.common.message.ServiceMessage;

/**
 * @author Amin
 */
public class EmptyException extends BusinessException {
    public EmptyException() {
        super(ServiceMessage.emptyValue(null));
    }
    public EmptyException(String message) {
        super(ServiceMessage.emptyValue(message));
    }
}
