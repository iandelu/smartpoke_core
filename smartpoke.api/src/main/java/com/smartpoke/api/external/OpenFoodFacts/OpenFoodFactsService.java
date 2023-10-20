package com.smartpoke.api.external.OpenFoodFacts;

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
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class OpenFoodFactsService {


//    @Autowired
//    private Product productService;
    private List<String> supermarkets = Arrays.asList("mercadona", "aldi", "carrefour");
    private Logger logger = LoggerFactory.getLogger(OpenFoodFactsService.class);

    private final RestTemplate restTemplate = new RestTemplate();
    private final OkHttpClient client = new OkHttpClient();
    @Value("${openfoodfacts.product-url}")
    private String productUrl;
    @Value("${openfoodfacts.filter}")
    private String filter;
    @Value("${openfoodfacts.supermarket-url}")
    private String supermarketUrl;



    public void syncProducts() {
        for (String supermarket : supermarkets){
            OpenFoodFactsResponse response = fetchProductsFromStore(supermarket, 1);
        }
    }
    public OpenFoodFactsResponse fetchProductsFromStore(String store, int page) {
        String url = String.format(supermarketUrl,store,filter,page);
        return restTemplate.getForObject(url, OpenFoodFactsResponse.class);
    }


    public String fetchProductDetails(String barcode) throws RuntimeException, IOException {
        OkHttpClient client = new OkHttpClient();
        String url = productUrl.concat(barcode).concat(filter).concat(".json");

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
