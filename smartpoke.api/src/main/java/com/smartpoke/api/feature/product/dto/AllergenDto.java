package com.smartpoke.api.feature.product.dto;

import lombok.Data;

@Data
public class AllergenDto {
    private String name;

    public AllergenDto(String name) {
        this.name = name;
    }
}