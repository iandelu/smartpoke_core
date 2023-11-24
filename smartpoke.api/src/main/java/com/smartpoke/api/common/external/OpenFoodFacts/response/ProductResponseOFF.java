package com.smartpoke.api.common.external.OpenFoodFacts.response;

import com.smartpoke.api.common.external.OpenFoodFacts.dto.ProductOFFDto;
import lombok.Data;

@Data
public class ProductResponseOFF {
    String code;
    ProductOFFDto product;
    String status;
    String status_verbose;
}
