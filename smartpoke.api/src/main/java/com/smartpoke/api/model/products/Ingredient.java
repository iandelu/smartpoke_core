package com.smartpoke.api.model.products;//package com.raccoon.smartpoke.model.products;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Ingredient {
    @Id
    private Long idCategory;

    private String name;
    private Integer type;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Product> products = new HashSet<>();

//    @ManyToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE})
//    @JoinTable(
//            name = "categories_recipes",
//            joinColumns = @JoinColumn(name = "category_id"),
//            inverseJoinColumns = @JoinColumn(name = "recipe_id")
//    )
//    private List<Recipe> recipes = new ArrayList<>();
}