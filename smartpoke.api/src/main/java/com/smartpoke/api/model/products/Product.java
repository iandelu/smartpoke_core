package com.smartpoke.api.model.products;//package com.raccoon.smartpoke.model.products;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Product {
    @Id
    private String ean;
    @Column(columnDefinition = "text")
    private String name;
    private String amount;
    private String nutriscore;
    @Column(columnDefinition = "text")
    private String description;
    @Column(length = 1000)
    private String brand;
    @Column(columnDefinition = "text")
    private String ingredientsText;
    private String origin;
    private String preservation;
    @Column(columnDefinition = "text")
    private String picture;
    private LocalDateTime lastUpdate;

    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "ean")
    private ProductMacronutrients productMacronutrients;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(
            name = "ingredients_products",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id")}
    )
    private List<Ingredient> ingredients;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(
            name = "allergens_products",
            joinColumns = {@JoinColumn(name = "allergen_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Set<Allergen> allergens;





}
