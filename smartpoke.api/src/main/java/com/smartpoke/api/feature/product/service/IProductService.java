package com.smartpoke.api.feature.product.service;

import com.smartpoke.api.feature.product.model.Product;

import java.util.List;

public interface IProductService {
    Product createProduct(Product product);

    Product updateProduct(String ean, Product product);

    List<Product> getAll();
    List<Product> saveAll(List<Product> products);
    Product findById(String id);

    void deleteUser(String id);

    List<Product> saveAllProducts(List<Product> products);

    Product saveProduct(Product product);

    void syncProducts();

    Product fetchProductDetails(String barcode);
}
