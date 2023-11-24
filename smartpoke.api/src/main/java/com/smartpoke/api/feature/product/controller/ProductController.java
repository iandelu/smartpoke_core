package com.smartpoke.api.feature.product.controller;

import com.smartpoke.api.feature.product.model.Product;
import com.smartpoke.api.feature.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController{

    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok().body(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getRecipe(@PathVariable String id){
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> createRecipe(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateUser(@PathVariable String id, @RequestBody Product product){
        return ResponseEntity.ok().body(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){productService.deleteUser(id);}

    @GetMapping("/syncOpenFoodFacts")
    public ResponseEntity<String> syncOpenFoodFacts(){
        try{
            productService.syncProducts();
            return ResponseEntity.ok().body("Initializing Sync openfoodfacts data manually");
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("An erro has occured"+e.getMessage());
        }

    }

    @GetMapping("/fetchProductInfo/{barcode}")
    public ResponseEntity<Product> fetchProductInfo(@PathVariable String barcode){
        return ResponseEntity.ok().body(productService.fetchProductDetails(barcode));
    }

}
