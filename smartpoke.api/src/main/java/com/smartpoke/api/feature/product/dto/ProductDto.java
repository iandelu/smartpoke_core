package com.smartpoke.api.feature.product.dto;

import com.smartpoke.api.common.model.Nutrients;
import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.category.model.Tag;
import com.smartpoke.api.feature.product.model.Allergen;
import com.smartpoke.api.feature.product.model.Product;
import com.smartpoke.api.feature.product.model.ProductNutrients;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProductDto implements IProductDto{


    private String ean;
    private String name;
    private String amount;
    private String nutriscore;
    private Integer novaGroup;
    private String description;
    private String brand;
    private String ingredients;
    private String origin;
    private String preservation;
    private String picture;
    private LocalDateTime lastUpdate;
    private Category category;
    private String keywords;
    private ProductNutrients productNutrients;
    private List<String> tags;
    private Set<AllergenDto> allergens;

    public ProductDto() {
    }

    public ProductDto(Product product){
        this.ean = product.getEan();
        this.name = product.getName();
        this.amount = product.getAmount();
        this.nutriscore = product.getNutriscore();
        this.novaGroup = product.getNovaGroup();
        this.description = product.getDescription();
        this.brand = product.getBrand();
        this.ingredients = product.getIngredients();
        this.origin = product.getOrigin();
        this.preservation = product.getPreservation();
        this.picture = product.getPicture();
        this.lastUpdate = product.getLastUpdate();
        this.category = product.getCategory();
        this.keywords = product.getKeywords();
        this.productNutrients = product.getProductNutrients();
        this.tags = new ArrayList<>();
        product.getTags().forEach(tag -> this.tags.add(tag.getName()));
        this.allergens = new HashSet<>();
        product.getAllergens().forEach(allergen -> this.allergens.add(new AllergenDto(allergen)));
    }
    @Override
    public Product toEntity() {
        Product entity = new Product();
        entity.setEan(this.ean);
        entity.setName(this.name);
        entity.setAmount(this.amount);
        entity.setNutriscore(this.nutriscore);
        entity.setNovaGroup(this.novaGroup);
        entity.setDescription(this.description);
        entity.setBrand(this.brand);
        entity.setIngredients(this.ingredients);
        entity.setOrigin(this.origin);
        entity.setPreservation(this.preservation);
        entity.setPicture(this.picture);
        entity.setLastUpdate(this.lastUpdate);
        entity.setCategory(this.category);
        entity.setKeywords(this.keywords);

        if (this.productNutrients != null) {
            productNutrients.setEan(this.ean);
            entity.setProductNutrients(productNutrients);
        }

        List<Tag> tags = new ArrayList<>();
        entity.setTags(tags);

        Set<Allergen> allergensEntity = new HashSet<>();
        if(this.allergens != null && !this.allergens.isEmpty()){
            this.allergens.forEach(allergen -> allergensEntity.add(allergen.toEntity()));
        }
        entity.setAllergens(allergensEntity);

        return entity;
    }
}
