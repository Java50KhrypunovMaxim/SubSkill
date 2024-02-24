package com.subskill.exception;

import com.subskill.api.ValidationConstants;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.AuthenticationException;

import java.util.Date;


public class RegistrationUserNotFoundException extends AuthenticationException {
    Date timestamp;

    public RegistrationUserNotFoundException() {
        super(ValidationConstants.REGISTRATION_USER_NOT_FOUND);
        this.timestamp = new Date();
    }


}
