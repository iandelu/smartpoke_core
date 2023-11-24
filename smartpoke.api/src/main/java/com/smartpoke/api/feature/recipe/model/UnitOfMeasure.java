package com.smartpoke.api.feature.recipe.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Data
@Entity
public class UnitOfMeasure {

    @Id
    private String description;
}