package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;
import org.springframework.transaction.TransactionSystemException;

@SuppressWarnings("serial")
public class NoUserInRepositoryException extends TransactionSystemException {
    public NoUserInRepositoryException() {
        super(ServiceExceptionMessages.TRANSACTIONAL_ERROR);
    }
}
