package com.smartpoke.api.feature.product.service;

import com.smartpoke.api.common.exceptions.ResourceNotFoundException;
import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.category.model.Tag;
import com.smartpoke.api.feature.category.service.CategoryService;
import com.smartpoke.api.feature.category.service.TagService;
import com.smartpoke.api.feature.product.dto.ProductDto;
import com.smartpoke.api.integrations.OpenFoodFacts.OpenFoodFactsClient;
import com.smartpoke.api.feature.product.model.Allergen;
import com.smartpoke.api.feature.product.model.Product;
import com.smartpoke.api.feature.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OpenFoodFactsClient openFoodFactsClient;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AllergenService allergenService;

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto product) {

        return new ProductDto(this.saveProduct(product.toEntity()));
    }

    @Override
    public ProductDto updateProduct(String ean, ProductDto product) {
        if (productRepository.existsById(ean)) {
            product.setEan(ean);
            return new ProductDto(productRepository.save(product.toEntity()));
        } else {
            return null;
        }
    }

    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ProductDto> saveAll(List<ProductDto> products) {
        List<Product> productsEntity = products.stream().map(ProductDto::toEntity).toList();
        return productRepository.saveAll(productsEntity).stream().map(ProductDto::new).toList();
    }

    @Override
    public ProductDto findById(String id) {
        return new ProductDto(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
    }

    @Override
    public void deleteUser(String id) {productRepository.deleteById(id);}

    @Override
    public List<ProductDto> saveAllProducts(List<Product> products) {
        List<ProductDto> updatedProducts = new ArrayList<>();
        ProductDto updateProduct = new ProductDto();

        for (Product product : products) {
            updateProduct = new ProductDto(this.saveProduct(product));
            updatedProducts.add(updateProduct);
        }

        return updatedProducts;
    }

    @Override
    public Product saveProduct(Product product) {
        Optional<Product> productOptional = Optional.empty();
        if (product.getEan() != null) {
            productOptional = productRepository.findById(product.getEan());
        }

        if (productOptional.isPresent()) {
            return productOptional.get();
        }

        List<Tag> updatedTags = new ArrayList<>();
        Set<Allergen> updatedAllergens = new HashSet<>();

        if (product.getCategory() != null) {
            Category category = categoryService.saveCategory(product.getCategory().getName(), product.getCategory().getEmoji(), product.getCategory().getLan());
            product.setCategory(category);
        }

        for (Tag tag : product.getTags()) {
            Tag tagEntity = tagService.saveTag(tag.getName(), tag.getLan());
            updatedTags.add(tagEntity);

        }

        for (Allergen allergen : product.getAllergens()){
            Allergen allergenEntity = allergenService.saveAllergen(allergen.getName(), allergen.getLan());
            updatedAllergens.add(allergenEntity);
        }

        product.setTags(updatedTags);
        product.setAllergens(updatedAllergens);
        product = productRepository.save(product);
        return product;
    }


    @Override
    @Scheduled(cron = "0 0 0 ? * SUN")
    public List<ProductDto> syncProducts() {

        List<Product> products = openFoodFactsClient.syncProducts();
        return saveAllProducts(products);

    }

    @Override
    public ProductDto fetchProductDetails(String barcode) {
        Product product =  openFoodFactsClient.fetchProductDetails(barcode);
        return new ProductDto(this.saveProduct(product));
    }

    @Override
    public Product findOrCreateProduct(String productName) {
        return productRepository.findByName(productName)
                .orElseGet(() -> createNewGenericProduct(productName));
    }

    private Product createNewGenericProduct(String productName) {
        Product product = new Product();
        product.setName(productName);
        product.setBrand("Generic");
        return saveProduct(product);
    }
}
