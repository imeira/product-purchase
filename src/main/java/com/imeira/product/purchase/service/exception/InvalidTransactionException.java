package com.imeira.product.purchase.service.exception;

import java.io.Serializable;

public class InvalidTransactionException extends RuntimeException implements Serializable  {

    private static final long serialVersionUID = 1L;

    public InvalidTransactionException(String message) {
        super(message);
    }

}
