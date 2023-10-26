package com.smartpoke.api.external.OpenFoodFacts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartpoke.api.dto.AllergenDto;
import com.smartpoke.api.dto.ProductDto;
import com.smartpoke.api.model.products.Allergen;
import com.smartpoke.api.model.products.Ingredient;
import com.smartpoke.api.model.products.Product;
import com.smartpoke.api.model.products.ProductMacronutrients;
import lombok.Data;

import java.time.LocalDate;
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
    private List<IngredientOFFDto> ingredientsList;


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

        List<Ingredient> ingredientSet = new ArrayList<>();
        if(this.ingredientsList != null && !this.ingredientsList.isEmpty()){
            this.ingredientsList.forEach(ingredient -> ingredientSet.add(ingredient.toEntity()));
        }
        entity.setIngredients(ingredientSet);

        Set<Allergen> allergens = new HashSet<>();
        if(this.allergens != null && !this.allergens.isEmpty()){
            this.allergens.forEach(allergen -> allergens.add(new Allergen(allergen.getName())));
        }
        entity.setAllergens(allergens);

        return entity;
    }
}