package com.prankur.microservices.product.service;

import com.prankur.microservices.product.dto.ProductRequest;
import com.prankur.microservices.product.dto.ProductResponse;
import com.prankur.microservices.product.models.Product;
import com.prankur.microservices.product.repository.ProductRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService
{
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest)
    {
//        log.info("it is: " + productRequest.name());
        Product product = Product.builder()
                                .name(productRequest.name())
                                .description(productRequest.description())
                                .price(productRequest.price())
                                .build();
        productRepository.save(product);
        log.info("Product " + productRequest.name() + " created successfully");

        return new ProductResponse(product.getName(),product.getDescription(),product.getPrice());

    }

    public List<ProductResponse> getAllProducts()
    {
        List<Product> productList = productRepository.findAll();
        List<ProductResponse> productResponseList = productList.stream()
                                                            .map( product -> new ProductResponse(product.getName(),product.getDescription(),product.getPrice()))
                                                            .collect(Collectors.toList());
        return productResponseList;


    }
}
