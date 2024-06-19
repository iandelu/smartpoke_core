package com.smartpoke.api.feature.product.service;

import com.smartpoke.api.feature.product.dto.ProductDto;
import com.smartpoke.api.feature.product.model.Product;

import java.util.List;

public interface IProductService {
    Product createProduct(Product product);
    Product saveProduct(Product product);
    Product updateProduct(String ean, Product product);
    List<Product> getAll();
    List<Product> saveAll(List<Product> products);
    Product findById(Long id);
    Product findByEan(String ean);
    List<Product> saveAllProducts(List<Product> products);
    List<Product> syncProducts();
    Product fetchProductDetails(String barcode);
    Product findOrCreateProduct(String tokens);
    void saveProductInCache(Long id, Product product);
    Product getProductFromCache(Long id);
}
