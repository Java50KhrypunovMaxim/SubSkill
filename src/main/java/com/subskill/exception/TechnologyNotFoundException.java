package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;

public class TechnologyNotFoundException extends NotFoundException {

    public TechnologyNotFoundException() {
        super(ServiceExceptionMessages.TECHNOLOGY_NOT_FOUND);

    }

}
