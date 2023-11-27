package com.smartpoke.api.feature.product.service;

import com.smartpoke.api.common.exceptions.ResourceNotFoundException;
import com.smartpoke.api.common.external.OpenFoodFacts.OpenFoodFactsClient;
import com.smartpoke.api.feature.product.model.Ingredient;
import com.smartpoke.api.feature.product.model.Product;
import com.smartpoke.api.feature.product.repository.IngredientRepository;
import com.smartpoke.api.feature.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OpenFoodFactsClient openFoodFactsClient;
    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(String ean, Product product) {
        if (productRepository.existsById(ean)) {
            product.setEan(ean);
            return productRepository.save(product);
        } else {
            return null;
        }
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Product> saveAll(List<Product> thisProducts) {
        return productRepository.saveAll(thisProducts);
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public void deleteUser(String id) {productRepository.deleteById(id);}

    @Override
    public List<Product> saveAllProducts(List<Product> products) {
        List<Product> updatedProducts = new ArrayList<>();
        Product updateProduct = new Product();

        for (Product product : products) {
            updateProduct = saveProduct(product);
            updatedProducts.add(updateProduct);
        }

        return updatedProducts;
    }

    @Override
    public Product saveProduct(Product product) {
        List<Ingredient> updatedIngredients = new ArrayList<>();
        Product updateProduct;

        for (Ingredient ingredient : product.getIngredients()) {
            Optional<Ingredient> existingIngredient = ingredientRepository.findByName(ingredient.getName());

            if (existingIngredient.isPresent()) {
                updatedIngredients.add(existingIngredient.get());
            } else {
                updatedIngredients.add(ingredient);
            }
        }

        product.setIngredients(updatedIngredients);
        updateProduct = productRepository.save(product);
        return updateProduct;
    }


    @Override
    @Scheduled(cron = "0 0 0 ? * SUN")
    public List<Product> syncProducts() {

        List<Product> products = openFoodFactsClient.syncProducts();
        return saveAll(products);

    }

    @Override
    public Product fetchProductDetails(String barcode) {
        Product product =  openFoodFactsClient.fetchProductDetails(barcode);
        return saveProduct(product);
    }
}
