package com.smartpoke.api.feature.product.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class AllergenKey {
    private String name;
    private String lan;

}
