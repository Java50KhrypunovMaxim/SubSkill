package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;
import org.springframework.dao.DataAccessException;

@SuppressWarnings("serial")
public class NoUserInRepositoryException extends DataAccessException {
    public NoUserInRepositoryException() {
        super(ServiceExceptionMessages.USER_NOT_FOUND);
    }
}
