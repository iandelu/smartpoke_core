package com.smartpoke.api.feature.product.model;//package com.raccoon.smartpoke.model.products;

import com.smartpoke.api.common.model.Nutrients;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ProductNutrients extends Nutrients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ean;
    private Integer amount;
    private Integer calories;
    private Double fats;
    private Double saturatedFats;
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

    @OneToOne(mappedBy = "productNutrients")
    private Product product;
}
