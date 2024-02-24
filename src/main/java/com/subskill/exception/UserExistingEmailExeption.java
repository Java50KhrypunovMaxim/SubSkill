package com.subskill.exception;


import com.subskill.api.ServiceExceptionMessages;

@SuppressWarnings("serial")
public class UserExistingEmailExeption extends RuntimeException {
    public UserExistingEmailExeption() {
        super(ServiceExceptionMessages.USER_EMAIL_ALREADY_EXISTS);
    }
}
