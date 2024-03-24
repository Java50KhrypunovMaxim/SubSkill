package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;
import com.subskill.api.ValidationConstants;

public class UnauthorizedAccessException extends NotFoundException{
    public UnauthorizedAccessException() {
        super(ValidationConstants.UNAUTHORIZED_USER);
    }
}
