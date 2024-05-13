package com.smartpoke.api.feature.recipe.model;

import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.reviews.model.Review;
import com.smartpoke.api.feature.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private Integer prepTime;
    private Double price;
    private Integer diners;
    private String picture;
    private String source;
    private String lan;
    private Double rating;
    private Integer views;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    @Column(columnDefinition = "text")
    private String videoUrl;

    @Enumerated(value = EnumType.STRING)
    private DifficultyEnum difficultyEnum;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private NutrientsRecipe nutrientsRecipe;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private List<RecipeStep> recipeSteps = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private Set<RecipeProduct> recipeProducts = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "creatorId", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;

    @PrePersist
    protected void onCreate() {
        lastUpdateDate = new Date();
    }
}