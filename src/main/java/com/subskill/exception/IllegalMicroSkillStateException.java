package com.subskill.exception;


import com.subskill.api.ServiceExceptionMessages;

@SuppressWarnings("serial")
public class IllegalMicroSkillStateException extends RuntimeException {
    public IllegalMicroSkillStateException() {
        super(ServiceExceptionMessages.MICROSKILL_ALREADY_EXISTS);
    }
}
