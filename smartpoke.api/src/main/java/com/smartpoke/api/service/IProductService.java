package com.smartpoke.api.service;

import com.smartpoke.api.model.products.Product;

import java.util.Set;

public interface IProductService {
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    Set<Product> getAll();
    Product findById();
}
