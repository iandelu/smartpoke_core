package com.smartpoke.api.integrations.OpenFoodFacts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartpoke.api.feature.category.model.Tag;
import com.smartpoke.api.feature.product.dto.AllergenDto;
import com.smartpoke.api.feature.product.dto.ProductDto;
import com.smartpoke.api.feature.product.model.Allergen;
import com.smartpoke.api.feature.product.model.Product;
import com.smartpoke.api.feature.product.model.ProductMacronutrients;
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
    @JsonProperty("generic_name")
    private String description;
    @JsonProperty("brands")
    private String brand;
    @JsonProperty("ingredients_text")
    private String ingredients;
    private String origin;
    private String preservation;
    @JsonProperty("image_front_url")
    private String picture;
    @JsonProperty("nutriments")
    private ProductMacronutrientsOFFDto informationMacronutrient;
    @JsonProperty("allergens_tags")
    private List<AllergenDto> allergens;
    @JsonProperty("categories_tags")
    private List<TagOFFDto> ingredientsList;
    @JsonProperty("_keywords")
    private List<String> keywords;


    @Override
    public Product toEntity() {
        Product entity = new Product();
        entity.setEan(this.ean);
        entity.setBrand(this.brand);
        entity.setName(this.name);
        entity.setAmount(this.amount);
        entity.setName(this.name);
        entity.setDescription(this.description);
        entity.setIngredientsText(this.ingredients);
        entity.setOrigin(this.origin);
        entity.setPreservation(this.preservation);
        entity.setPicture(this.picture);
        entity.setNutriscore(this.nutriscore);
        entity.setLastUpdate(LocalDateTime.now());
        if (this.informationMacronutrient != null) {
            ProductMacronutrients macronutrientesEntity = this.informationMacronutrient.toEntity();
            macronutrientesEntity.setEan(this.ean);
            entity.setProductMacronutrients(macronutrientesEntity);
        }

        List<Tag> ingredientSet = new ArrayList<>();
        if(this.ingredientsList != null && !this.ingredientsList.isEmpty()){
            this.ingredientsList.forEach(ingredient -> ingredientSet.add(ingredient.toEntity()));
        }
        entity.setTags(ingredientSet);

        Set<Allergen> allergens = new HashSet<>();
        if(this.allergens != null && !this.allergens.isEmpty()){
            this.allergens.forEach(allergen -> allergens.add(new Allergen(allergen.getName())));
        }
        entity.setAllergens(allergens);

        return entity;
    }
}