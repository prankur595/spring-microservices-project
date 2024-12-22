package com.prankur.microservices.order.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class QuantityNotPresentExceptionResponseDTO
{
    private String errorCode;
    private String errDescription;
    private Date date;
}
