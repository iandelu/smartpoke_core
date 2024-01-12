package com.smartpoke.api.integrations.OpenFoodFacts.response;

import com.smartpoke.api.integrations.OpenFoodFacts.dto.ProductOFFDto;
import lombok.Data;

import java.util.List;

@Data
public class OFFResponse {
    private int count;
    private int page;
    private int page_count;
    private int page_size;
    private List<ProductOFFDto> products;

}
