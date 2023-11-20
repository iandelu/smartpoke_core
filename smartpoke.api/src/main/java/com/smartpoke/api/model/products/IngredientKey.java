package com.smartpoke.api.model.products;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class IngredientKey {
    private String name;
    private String lan;
}
