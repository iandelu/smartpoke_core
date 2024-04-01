package com.smartpoke.api.feature.product.dto;

import com.smartpoke.api.feature.category.model.Tag;
import com.smartpoke.api.feature.product.model.Allergen;
import lombok.Data;

@Data
public class AllergenDto {
    private String name;
    private String lan;

    public AllergenDto(String offTag) {
        textToAllergen(offTag);
    }
    public AllergenDto(Allergen allergen) {
        this.name = allergen.getName();
        this.lan = allergen.getLan();
    }

    private void textToAllergen(String message){
        String[] parts = message.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Formato de ingrediente inválido");
        }

        this.lan = parts[0].trim();
        this.name = parts[1].trim();
    }

    public Allergen toEntity(){
        Allergen allergen = new Allergen();
        allergen.setLan(this.lan);
        allergen.setName(this.name);

        return allergen;
    }
}