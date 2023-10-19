package com.smartpoke.api.model.products;//package com.raccoon.smartpoke.model.products;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class ProductMacronutrients {
    @Id
    private Long id;
    private Integer amount;
    private Integer calories;
    private Double fats;
    private Double cabs;
    private Double fibre;
    private Double protein;
    private Double salt;

}