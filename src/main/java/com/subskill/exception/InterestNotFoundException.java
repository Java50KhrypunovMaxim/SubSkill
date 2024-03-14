package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;

public class InterestNotFoundException extends NotFoundException {
    public InterestNotFoundException() {
        super(ServiceExceptionMessages.INTEREST_NOT_FOUND);
    }
}
