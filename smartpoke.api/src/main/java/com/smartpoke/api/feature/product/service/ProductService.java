package com.smartpoke.api.feature.product.service;

import com.smartpoke.api.common.exceptions.ResourceNotFoundException;
import com.smartpoke.api.common.utils.NormalizerUtils;
import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.category.model.Tag;
import com.smartpoke.api.feature.category.service.CategoryService;
import com.smartpoke.api.feature.category.service.TagService;
import com.smartpoke.api.feature.product.specification.ProductSpecification;
import com.smartpoke.api.integrations.OpenFoodFacts.OpenFoodFactsClient;
import com.smartpoke.api.feature.product.model.Allergen;
import com.smartpoke.api.feature.product.model.Product;
import com.smartpoke.api.feature.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    private static final String PRODUCT_CACHE_KEY = "Product:";

    @Override
    @Transactional
    public Product createProduct(Product product) {
        Product productOptional = null;
        List<Tag> updatedTags = new ArrayList<>();
        Set<Allergen> updatedAllergens = new HashSet<>();

        if (product.getEan() != null && product.getDescription() != null) {
            productOptional = productRepository.findFirstByEanOrName(product.getEan(), product.getDescription()).orElseGet(() -> null);
            if (productOptional != null) {
                return productOptional;
            }
        }

        if (product.getCategory() != null) {
            Category category = categoryService.saveCategory(product.getCategory().getName(), product.getCategory().getEmoji(), product.getCategory().getLan());
            product.setCategory(category);
        }

        if (product.getTags() != null){
            updatedTags = tagService.saveAllTags(product.getTags());
            product.setTags(updatedTags);
        }

        if (product.getAllergens() != null){
            for (Allergen allergen : product.getAllergens()){
                Allergen allergenEntity = allergenService.saveAllergen(allergen.getName(), allergen.getLan());
                updatedAllergens.add(allergenEntity);
            }
            product.setAllergens(updatedAllergens);
        }

        //Relation with generic product
        if(!product.getBrand().equals("Generic")) {
            String[] tokens = NormalizerUtils.tokenizeIngredient(product.getName());
            String tokensString = String.join(" ", tokens);
            Product genericProduct = this.findOrCreateProduct(tokensString);
            product.setGenericProduct(genericProduct);
        }
        return this.saveProduct(product);
    }

    @Override
    public Product saveProduct(Product product) {
        var prouductName = productRepository.findMostSimilarByName(product.getName());
        if (prouductName.isPresent()) {
            return prouductName.get();
        }
        if (product.getEan() != null) {
            var productFound = productRepository.findFirstByEan(product.getEan());
            if (productFound.isPresent()) {
                return productFound.get();
            }
        }



        return productRepository.save(product);

    }

    @Override
    public Product updateProduct(String ean, Product product) {
        if (productRepository.existsByEan(ean)) {
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
    public List<Product> saveAll(List<Product> products) {
        return this.saveAllProducts(products);
    }

    @Override
    public Product findById(Long id) {
        Product product = getProductFromCache(id);
        if (product == null) {
            product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            saveProductInCache(id, product);
        }
        return product;
    }

    @Override
    public Product findByEan(String ean) {
        return productRepository.findFirstByEan(ean).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public List<Product> saveAllProducts(List<Product> products) {
        return products.stream().map(this::saveProduct).toList();
    }

    @Override
    @Scheduled(cron = "0 0 0 ? * SUN")
    public List<Product> syncProducts() {
        List<Product> products = openFoodFactsClient.syncProducts();
        return saveAllProducts(products);
    }

    @Override
    public Product fetchProductDetails(String barcode) {

        Product product =  productRepository.findFirstByEan(barcode).orElseGet(() -> {
            Product productFromOpenFoodFacts = openFoodFactsClient.fetchProductDetails(barcode);
            return productFromOpenFoodFacts != null ? createProduct(productFromOpenFoodFacts) : null;
        });
        return this.createProduct(product);
    }

    @Override
    public Product findOrCreateProduct(String tokens) {
        return productRepository.findMostSimilarByName(tokens)
                .orElseGet(() -> createNewGenericProduct(String.join(" ", tokens)));
    }

    private Product createNewGenericProduct(String productName) {
        Product product = new Product();
        product.setName(productName);
        product.setBrand("Recipe");
        return this.saveProduct(product);
    }

    @Override
    public void saveProductInCache(Long id, Product product) {
        redisTemplate.opsForValue().set(PRODUCT_CACHE_KEY + id, product);
    }
    @Override
    public Product getProductFromCache(Long id) {
        return (Product) redisTemplate.opsForValue().get(PRODUCT_CACHE_KEY + id);
    }

    @Override
    public Page<Product> filterProducts(String ean, String name, String brand, String category, String keywords, List<String> tags, List<String> allergens, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<Product> spec = ProductSpecification.nameLike(name)
                .and(ProductSpecification.brandLike(brand))
                .and(ProductSpecification.categoryLike(category))
                .and(ProductSpecification.keywordsLike(keywords))
                .and(ProductSpecification.tagsIn(tags))
                .and(ProductSpecification.allergensIn(allergens));

        return productRepository.findAll(spec, pageable);
    }
}
