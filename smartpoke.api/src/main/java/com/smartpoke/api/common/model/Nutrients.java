package com.smartpoke.api.common.model;


import lombok.Data;

@Data
public abstract class Nutrients {

    private Integer amount;
    private Integer calories;
    private Double fats;
    private Double cabs;
    private Double fibre;
    private Double protein;
    private Double salt;

    // MicroNutrients
    private Double alcohol;  // grams
    private Double water;     // grams
    private Double caffeine;  // milligrams
    private Double sugars;    // grams
    private Double calcium;   // milligrams
    private Double iron;     // milligrams
    private Double magnesium; // milligrams
    private Double phosphorus;// milligrams
    private Double potassium; // milligrams
    private Double sodium;    // milligrams
    private Double zinc;      // milligrams
    private Double copper;    // milligrams
    private Double retinol;   // micrograms
    private Double vitaminA;  // micrograms RAE
    private Double vitaminE;  // milligrams
    private Double vitaminD;  // micrograms
    private Double vitaminC;  // milligrams
    private Double vitaminB6; // milligrams
    private Double vitaminB12;// micrograms
    private Double vitaminK;  // micrograms
    private Double thiamin;   // milligrams
    private Double niacin;    // milligrams
}
