package com.smartpoke.api.integrations.OpenFoodFacts.response;

import com.smartpoke.api.integrations.OpenFoodFacts.dto.ProductOFFDto;
import lombok.Data;

@Data
public class ProductResponseOFF {
    String code;
    ProductOFFDto product;
    String status;
    String status_verbose;
}
