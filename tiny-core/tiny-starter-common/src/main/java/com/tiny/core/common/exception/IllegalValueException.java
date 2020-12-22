package com.tiny.core.common.exception;


import com.tiny.core.common.message.ServiceMessage;


/**
 * @author Amin
 */
public class IllegalValueException  extends BusinessException {

    public IllegalValueException() {
        super(ServiceMessage.illegalValue(null));
    }

    public IllegalValueException(String message) {
        super(ServiceMessage.illegalValue(message));
    }
}
