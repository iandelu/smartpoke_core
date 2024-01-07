package com.smartpoke.api.feature.planner.model;

import com.smartpoke.api.feature.recipe.model.Recipe;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numDay;
    private Integer numMeal;

    @Enumerated(value = EnumType.STRING)
    private MealTimeEnum mealTimeEnum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_planner_id")
    private MealPlanner mealPlanner;
}
