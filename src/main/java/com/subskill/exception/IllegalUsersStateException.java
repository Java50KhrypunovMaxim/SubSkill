package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;

public class IllegalUsersStateException extends IllegalStateException {
	public IllegalUsersStateException() {
		super(ServiceExceptionMessages.USER_ALREADY_EXISTS);
	}
}
