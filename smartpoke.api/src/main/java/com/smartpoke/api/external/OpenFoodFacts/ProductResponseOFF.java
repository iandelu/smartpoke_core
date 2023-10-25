package com.smartpoke.api.external.OpenFoodFacts;

import lombok.Data;

@Data
public class ProductResponseOFF {
    String code;
    ProductOFFDto product;
    String status;
    String status_verbose;
}
