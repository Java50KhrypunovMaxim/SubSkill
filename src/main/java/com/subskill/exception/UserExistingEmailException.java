package com.subskill.exception;


import com.subskill.api.ServiceExceptionMessages;

@SuppressWarnings("serial")
public class UserExistingEmailException extends RuntimeException {
    public UserExistingEmailException() {
        super(ServiceExceptionMessages.USER_EMAIL_ALREADY_EXISTS);
    }
}
