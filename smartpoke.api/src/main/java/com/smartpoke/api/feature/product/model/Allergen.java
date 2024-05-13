package com.smartpoke.api.feature.product.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
public class Allergen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lan;

    @ManyToMany(mappedBy = "allergens")
    private Set<Product> products = new HashSet<>();

    public Allergen(String name) {
        this.setLan(name.substring(0,2));
        this.setName(name.substring(3));
    }

    public Allergen(String name, String lan) {
        this.setLan(lan);
        this.setName(name);
    }
}