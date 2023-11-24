package com.smartpoke.api.feature.recipe.model;

import com.smartpoke.api.feature.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer prepTime;
    private Double price;
    private Integer diners;
    private String picture;
    private String source;

    @Enumerated(value = EnumType.STRING)
    private DifficultyEnum difficultyEnum;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private Set<RecipeStep> recipeSteps = new HashSet<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecipeIngredients> recipeIngredients = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "creatorId", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;



}