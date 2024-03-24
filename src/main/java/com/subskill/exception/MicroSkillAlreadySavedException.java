package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;

public class MicroSkillAlreadySavedException extends RuntimeException {
    public MicroSkillAlreadySavedException () {
        super(ServiceExceptionMessages.MICROSKILL_ALREADY_SAVED);
    }
}
