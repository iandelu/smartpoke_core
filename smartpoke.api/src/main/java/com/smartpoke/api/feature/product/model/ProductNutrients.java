package com.smartpoke.api.feature.product.model;//package com.raccoon.smartpoke.model.products;

import com.smartpoke.api.common.model.Nutrients;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductNutrients extends Nutrients {
    @Id
    private String ean;

}
