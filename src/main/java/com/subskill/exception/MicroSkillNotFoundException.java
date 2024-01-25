package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;


@SuppressWarnings("serial")
public class MicroSkillNotFoundException extends NotFoundException {

    public  MicroSkillNotFoundException() {
        super(ServiceExceptionMessages.MICROSKILL_NOT_FOUND);

    }

}
