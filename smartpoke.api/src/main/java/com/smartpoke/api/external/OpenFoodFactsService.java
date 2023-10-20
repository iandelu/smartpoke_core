package com.smartpoke.api.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartpoke.api.model.products.Product;
import com.smartpoke.api.service.IProductService;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OpenFoodFactsService {

    Logger logger = LoggerFactory.getLogger(OpenFoodFactsService.class);
    private final OkHttpClient client = new OkHttpClient();
    private static final String GET_PRODUCT = "https://world.openfoodfacts.org/api/v2/product/";
    private final String FILTER = "?fields=product_name,code,allergens_tags,allergens,image_front_url,brands,id,ingredients_tags,ingredients_text,nutriments,countries,labels,nutrition_grades,categories,categories_tags,generic_name,image_front_small_url,ingredients_hierarchy,ingredients_tags,ingredients_original_tags,ingredients_text_es,quantity";

    private final IProductService productService;

    @Autowired
    public OpenFoodFactsService(IProductService productService) {
        this.productService = productService;
    }

    public void syncProducts() {

        String barcode = "3274080005003";

        try {
            String jsonProduct = fetchProductDetails(barcode);
        } catch (IOException | RuntimeException e) {
            logger.info("{}", e.getMessage());
        }
    }
    public String fetchProductDetails(String barcode) throws RuntimeException, IOException {
        OkHttpClient client = new OkHttpClient();

        String url = GET_PRODUCT.concat(barcode).concat(FILTER);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new RuntimeException("Failed to fetch data: " + response);
        }

        return response.body().string();
    }


    public Product parseJsonToProduct(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(json, Product.class);
        return product;
    }
}
