package com.prankur.microservices.inventory.service;

import com.prankur.microservices.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService
{
    private final InventoryRepository inventoryRepository;

    public boolean inStock(String skuCode, Integer quantity)
    {
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}
