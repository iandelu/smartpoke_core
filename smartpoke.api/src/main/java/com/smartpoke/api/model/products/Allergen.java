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
        id.setLan(name.substring(0,2));
        id.setName(name.substring(4,name.length()));
    }
}