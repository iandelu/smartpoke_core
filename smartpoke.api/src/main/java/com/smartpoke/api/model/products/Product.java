package com.smartpoke.api.model.products;//package com.raccoon.smartpoke.model.products;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ean;
    private String name;
    private String nutriscore;
    private String description;
    private String brand;
    private String ingredientsText;
    private String origin;
    private String preservation;
    private String picture;

    @OneToOne(cascade = CascadeType.ALL)
    private ProductMacronutrients productMacronutrients;

    @ManyToMany(mappedBy = "products")
    private List<Ingredient> aliments;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(
            name = "allergens_products",
            joinColumns = {@JoinColumn(name = "allergen_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Set<Allergen> allergens = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(
            name = "ingredients_products",
            joinColumns = {@JoinColumn(name = "ingredient_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Set<Ingredient> ingredients = new HashSet<>();

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ProductSupermarket> productSupermarkets;


}
