package com.smartpoke.api.integrations.OpenFoodFacts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartpoke.api.feature.category.model.Tag;
import com.smartpoke.api.feature.product.dto.AllergenDto;
import com.smartpoke.api.feature.product.dto.ProductDto;
import com.smartpoke.api.feature.product.model.Allergen;
import com.smartpoke.api.feature.product.model.Product;
import com.smartpoke.api.feature.product.model.ProductNutrients;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProductOFFDto implements ProductDto {
    @JsonProperty("code")
    private String ean;
    @JsonProperty("product_name")
    private String name;
    @JsonProperty("quantity")
    private String amount;
    @JsonProperty("nutrition_grades")
    private String nutriscore;
    @JsonProperty("nova_group")
    private Integer novaGroup;
    @JsonProperty("generic_name")
    private String description;
    @JsonProperty("brands")
    private String brand;
    @JsonProperty("ingredients_text")
    private String ingredients;
    private String origin;
    private String preservation;

    @JsonProperty("compared_to_category")
    private String category;
    @JsonProperty("image_front_small_url")
    private String picture;
    @JsonProperty("nutriments")
    private ProductMacronutrientsOFFDto informationMacronutrient;
    @JsonProperty("allergens_tags")
    private List<AllergenDto> allergens;
    @JsonProperty("categories_tags")
    private List<TagOFFDto> tags;
    @JsonProperty("_keywords")
    private List<String> keywords;


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

        entity.setLastUpdate(LocalDateTime.now());
        entity.setCategory(this.category);

        if (this.informationMacronutrient != null) {
            ProductNutrients macronutrientesEntity = this.informationMacronutrient.toEntity();
            macronutrientesEntity.setEan(this.ean);
            entity.setProductNutrients(macronutrientesEntity);
        }

        List<Tag> tags = new ArrayList<>();
        if(this.tags != null && !this.tags.isEmpty()){
            this.tags.forEach(tag -> tags.add(tag.toEntity()));
        }
        entity.setTags(tags);

        Set<Allergen> allergens = new HashSet<>();
        if(this.allergens != null && !this.allergens.isEmpty()){
            this.allergens.forEach(allergen -> allergens.add(allergen.toEntity()));
        }

        entity.setAllergens(allergens);

        return entity;
    }
}