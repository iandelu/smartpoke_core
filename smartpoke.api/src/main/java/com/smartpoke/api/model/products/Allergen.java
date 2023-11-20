package com.smartpoke.api.model.products;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
public class Allergen {
    @EmbeddedId
    private AllergenKey id;

    @ManyToMany(mappedBy = "allergens")
    private Set<Product> products = new HashSet<>();

    public Allergen(String name) {
        id = new AllergenKey();

        String[] parts = name.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Formato de ingrediente inválido");
        }

        id.setLan(parts[0].trim());
        id.setName(parts[1].trim());
    }
}