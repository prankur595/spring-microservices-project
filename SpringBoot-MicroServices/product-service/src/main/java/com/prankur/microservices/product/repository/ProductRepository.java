package com.prankur.microservices.product.repository;

import com.prankur.microservices.product.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>
{
}
