package com.prankur.microservices.order.dto;

import java.math.BigDecimal;

public record OrderRequestDTO(String skuCode, BigDecimal price, Integer quantity, UserDetails userdetails)
{
    public record UserDetails( String firstName, String lastName, String email){}

}
