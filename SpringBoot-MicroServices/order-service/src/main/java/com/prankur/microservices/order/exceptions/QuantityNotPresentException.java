package com.prankur.microservices.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =   HttpStatus.NOT_ACCEPTABLE, reason = "Requested Quantity not present.")
public class QuantityNotPresentException extends RuntimeException
{
    public QuantityNotPresentException(String message)
    {
        super(message);
    }
}
