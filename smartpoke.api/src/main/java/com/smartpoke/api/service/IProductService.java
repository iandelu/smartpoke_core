package com.smartpoke.api.service;

import com.smartpoke.api.model.products.Product;

import java.util.List;
import java.util.Set;

public interface IProductService {
    Product createProduct(Product product);

    Product updateProduct(String ean, Product product);

    List<Product> getAll();
    List<Product> saveAll(List<Product> products);
    Product findById(String id);

    void deleteUser(String id);
}
