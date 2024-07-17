package com.smartpoke.api.feature.category.model;

import com.smartpoke.api.feature.product.model.Product;
import com.smartpoke.api.feature.recipe.model.Recipe;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String lan;
    private String emoji;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Recipe> recipes = new HashSet<>();
}
