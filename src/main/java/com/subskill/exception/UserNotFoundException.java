package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(ServiceExceptionMessages.USER_NOT_FOUND);
    }
}
