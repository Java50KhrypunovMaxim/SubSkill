package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;
import org.springframework.transaction.TransactionSystemException;

public class TechnologyNotFoundException extends NotFoundException {

    public TechnologyNotFoundException() {
        super(ServiceExceptionMessages.TECHNOLOGY_NOT_FOUND);

    }

}
