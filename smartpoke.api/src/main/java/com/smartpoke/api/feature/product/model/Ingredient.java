package com.smartpoke.api.feature.product.model;//package com.raccoon.smartpoke.model.products;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String language;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Product> products = new HashSet<>();

}