package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;

@SuppressWarnings("serial")
public class NoUserInRepositoryException extends RuntimeException {
    public NoUserInRepositoryException() {
        super(ServiceExceptionMessages.USER_NOT_FOUND);
    }
}
