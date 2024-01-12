package com.smartpoke.api.feature.planner.model;

import com.smartpoke.api.feature.recipe.model.Recipe;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private MealType mealType;

    private LocalDate date;

    @ManyToMany
    @JoinTable(
            name = "meal_recipe",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipes;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
}
