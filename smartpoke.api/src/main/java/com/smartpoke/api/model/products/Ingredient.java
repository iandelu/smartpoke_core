package com.smartpoke.api.model.products;//package com.raccoon.smartpoke.model.products;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Ingredient {

    @EmbeddedId
    private IngredientKey id;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Product> products = new HashSet<>();

    public Ingredient(String name) {
        id = new IngredientKey();

        String[] parts = name.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Formato de ingrediente inválido");
        }

        id.setLan(parts[0].trim());
        id.setName(parts[1].trim());
    }

}