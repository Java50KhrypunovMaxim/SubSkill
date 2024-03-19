package com.subskill.exception;

import com.subskill.api.ServiceExceptionMessages;

public class CartNotFoundException extends NotFoundException {
    public CartNotFoundException() {
        super(ServiceExceptionMessages.CART_NOT_FOUND);
    }
}
