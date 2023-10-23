package com.smartpoke.api.dto;

import lombok.Data;

@Data
public class AllergenDto {
    private String name;

    public AllergenDto(String name) {
        this.name = name;
    }
}