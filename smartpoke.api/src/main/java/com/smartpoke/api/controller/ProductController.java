package com.smartpoke.api.controller;

import com.smartpoke.api.external.OpenFoodFacts.OpenFoodFactsService;
import com.smartpoke.api.model.products.Product;
import com.smartpoke.api.model.recipes.Recipe;
import com.smartpoke.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/products")
public class ProductController{

    @Autowired
    private ProductService productService;

    @Autowired
    private OpenFoodFactsService openFoodFactsService;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateUser(@PathVariable String id, @RequestBody Product product){
        return ResponseEntity.ok().body(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){productService.deleteUser(id);}

    @GetMapping("/syncOpenFoodFacts")
    public ResponseEntity<String> syncOpenFoodFacts(){
        openFoodFactsService.syncProducts();
        return ResponseEntity.ok().body("Initializing Sync openfoodfacts data manually");
    }

    @GetMapping("/fetchProductInfo/{barcode}")
    public ResponseEntity<Product> fetchProductInfo(@PathVariable String barcode){
        return ResponseEntity.ok().body(openFoodFactsService.fetchProductDetails(barcode));
    }

}
