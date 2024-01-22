package com.subskill.exception;


@SuppressWarnings("serial")
public class UserExistingEmailExeption extends RuntimeException {
    public UserExistingEmailExeption(String message) {
        super(message);
    }
}
