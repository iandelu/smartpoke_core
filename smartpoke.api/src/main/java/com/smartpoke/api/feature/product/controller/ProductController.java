package com.smartpoke.api.feature.product.controller;

import com.smartpoke.api.feature.product.dto.ProductDto;
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
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok().body(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String id){
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductDto product){
        return ResponseEntity.ok().body(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id){productService.deleteUser(id);}

    @GetMapping("/syncOpenFoodFacts")
    public ResponseEntity<String> syncOpenFoodFacts(){
        try{
            List<ProductDto> products = productService.syncProducts();
            return ResponseEntity.ok().body("Initializing Sync openfoodfacts data manually\n"+products.toString());
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("An error has occurred"+e.getMessage());
        }

    }

    @GetMapping("/fetchProductInfo/{barcode}")
    public ResponseEntity<ProductDto> fetchProductInfo(@PathVariable String barcode){
        return ResponseEntity.ok().body(productService.fetchProductDetails(barcode));
    }

}
