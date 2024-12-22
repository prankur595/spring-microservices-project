package com.prankur.microservices.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
//@ControllerAdvice(basePackages = {"com.prankur.microservices.order.service"})
public class ExceptionHanlderAdvice
{
    @ExceptionHandler(value = QuantityNotPresentException.class)
    public ResponseEntity<QuantityNotPresentExceptionResponseDTO> handleQantityNotPresentException(QuantityNotPresentException exception)
    {
        QuantityNotPresentExceptionResponseDTO responseDTO = new QuantityNotPresentExceptionResponseDTO("HttpStatus.BAD_REQUEST.400", exception.getMessage(),
                                                                                                        new Date());
        return new ResponseEntity<QuantityNotPresentExceptionResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
