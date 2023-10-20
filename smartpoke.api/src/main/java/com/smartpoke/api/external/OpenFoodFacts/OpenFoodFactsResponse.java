package com.smartpoke.api.external.OpenFoodFacts;

import lombok.Data;

import java.util.List;

@Data
public class OpenFoodFactsResponse {
    private int count;
    private int page;
    private int page_count;
    private int page_size;
    private List<ProductDto> products;

}
