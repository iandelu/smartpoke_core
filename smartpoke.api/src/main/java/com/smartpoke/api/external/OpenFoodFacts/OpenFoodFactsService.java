package com.smartpoke.api.external.OpenFoodFacts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartpoke.api.model.products.Product;
import com.smartpoke.api.service.ProductService;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OpenFoodFactsService {

    @Autowired
    private ProductService productService;
    private List<String> stores = Arrays.asList("mercadona", "aldi", "carrefour");
    private Logger logger = LoggerFactory.getLogger(OpenFoodFactsService.class);

    private final RestTemplate restTemplate = new RestTemplate();
    private final OkHttpClient client = new OkHttpClient();
    @Value("${openfoodfacts.product-url}")
    private String productUrl;
    @Value("${openfoodfacts.filter}")
    private String filter;
    @Value("${openfoodfacts.supermarket-url}")
    private String supermarketUrl;



    @Scheduled(cron = "0 0 0 ? * SUN")
    public void syncProducts() {
        List<ProductOFFDto> productsStore = new ArrayList<ProductOFFDto>();
        for (String store : stores){
            productsStore.addAll(fetchProductsFromStore(store));
        }
        List<Product> productsEntity = new ArrayList<>();
        productsStore.forEach( p -> productsEntity.add(p.toEntity()));
        productService.saveAll(productsEntity);

    }
    public List<ProductOFFDto> fetchProductsFromStore(String store) {
        List<ProductOFFDto> products = new ArrayList<ProductOFFDto>();
        int page = 1;
        while (true) {
            String url = String.format(supermarketUrl, store, filter, page);
            OFFResponse response = restTemplate.getForObject(url, OFFResponse.class);

            if (response == null || response.getProducts() == null || response.getProducts().isEmpty()) {
                break;
            }

            products.addAll(response.getProducts());

            if (page >= response.getPage_size()) {
                break;
            }
            logger.info("Inserted new Products: {}", products);
            page++;
        }

        return products;
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
