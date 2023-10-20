package com.smartpoke.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Integer idProduct;

    @JsonProperty("code")
    private String ean;
    @JsonProperty("generic_name")
    private String name;
    @JsonProperty("quantity")
    private Integer amount;
    @JsonProperty("nutrition_grades")
    private String nutriscore;
    @JsonProperty("product_name")
    private String description;
    @JsonProperty("brands")
    private String brand;
    @JsonProperty("ingredients_text")
    private String ingredients;
    private String origen;
    private String conservation;
    @JsonProperty("image_front_url")
    private String picture;
    @JsonProperty("nutriments")
    private ProductsMacronutrientsDto informationMacronutrient;
    @JsonProperty("allergens_tags")
    private List<AllergenDto> allergens;

    // getters and setters...
}