package com.smartpoke.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Integer idProduct;
    private String ean;
    private Integer idAliment;
    private String name;
    private Integer amount;
    private String nutriscore;
    private String description;
    private String brand;
    private String ingredients;
    private String origen;
    private String conservation;
    private String picture;
    private ProductsMacronutrientsDto informationMacronutrient;
    private List<AllergenDto> allergens;

    // getters and setters...
}