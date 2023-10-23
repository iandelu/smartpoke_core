package com.smartpoke.api.model.products;//package com.raccoon.smartpoke.model.products;

import jakarta.persistence.*;
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

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Product product;
}