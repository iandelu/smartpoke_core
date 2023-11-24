package com.smartpoke.api.feature.product.model;//package com.raccoon.smartpoke.model.products;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductMacronutrients {
    @Id
    private String ean;
    private Integer amount;
    private Integer calories;
    private Double fats;
    private Double cabs;
    private Double fibre;
    private Double protein;
    private Double salt;

}