package com.smartpoke.api.feature.recipe.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RecipeStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text")
    private String description;
    private Integer position;
    private Integer time;
    private String picture;
}