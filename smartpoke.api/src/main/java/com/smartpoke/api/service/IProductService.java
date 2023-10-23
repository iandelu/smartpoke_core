package com.smartpoke.api.service;

import com.smartpoke.api.model.products.Product;

import java.util.List;
import java.util.Set;

public interface IProductService {
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    List<Product> getAll();
    List<Product> saveAll(List<Product> products);
    Product findById(Long id);

    void deleteUser(Long id);
}
