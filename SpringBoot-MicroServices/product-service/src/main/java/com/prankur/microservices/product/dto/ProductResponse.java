package com.prankur.microservices.product.dto;

import java.math.BigDecimal;

public record ProductResponse(String name, String description, BigDecimal price)
{
}
