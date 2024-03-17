package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionSystemException;

@SuppressWarnings("serial")
public class NoUserInRepositoryException extends DataAccessException {
    public NoUserInRepositoryException() {
        super(ServiceExceptionMessages.DATA_ACCESS_NOT_FOUND);
    }
}
