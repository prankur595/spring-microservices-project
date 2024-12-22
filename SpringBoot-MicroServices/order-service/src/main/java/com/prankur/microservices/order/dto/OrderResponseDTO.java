package com.prankur.microservices.order.dto;

import java.math.BigDecimal;

public record OrderResponseDTO(String orderNumber, String skuCode, BigDecimal price, Integer quantity)
{
}
