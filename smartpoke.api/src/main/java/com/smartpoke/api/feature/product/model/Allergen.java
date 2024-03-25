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
}