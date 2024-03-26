package com.subskill.exception;

import com.subskill.api.ValidationConstants;

public class SamePasswordException extends RuntimeException {
    public  SamePasswordException() {
        super(ValidationConstants.SAME_PASSWORD);

    }
}
