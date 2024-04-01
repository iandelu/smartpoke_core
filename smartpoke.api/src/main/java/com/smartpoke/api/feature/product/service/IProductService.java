package com.smartpoke.api.feature.product.service;

import com.smartpoke.api.feature.product.dto.ProductDto;
import com.smartpoke.api.feature.product.model.Product;

import java.util.List;

public interface IProductService {
    ProductDto createProduct(ProductDto product);

    ProductDto updateProduct(String ean, ProductDto product);

    List<ProductDto> getAll();
    List<ProductDto> saveAll(List<ProductDto> products);
    ProductDto findById(String id);

    void deleteUser(String id);

    List<ProductDto> saveAllProducts(List<Product> products);

    Product saveProduct(Product product);

    List<ProductDto> syncProducts();

    ProductDto fetchProductDetails(String barcode);
    Product findOrCreateProduct(String productName);
}
