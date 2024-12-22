package com.prankur.microservices.product.controller;

import com.prankur.microservices.product.dto.ProductRequest;
import com.prankur.microservices.product.dto.ProductResponse;
import com.prankur.microservices.product.models.Product;
import com.prankur.microservices.product.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController
{
    ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createproduct(@RequestBody ProductRequest productRequest)
    {
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts()
    {
        return productService.getAllProducts();
    }

}
