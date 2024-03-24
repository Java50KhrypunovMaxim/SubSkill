package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;

public class CartIsEmptyException extends RuntimeException {
    public CartIsEmptyException() {
        super(ServiceExceptionMessages.CART_IS_EMPTY);
    }
}
