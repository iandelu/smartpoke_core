package com.smartpoke.api.common.external.OpenFoodFacts;

import com.smartpoke.api.common.exceptions.ResourceNotFoundException;
import com.smartpoke.api.common.external.OpenFoodFacts.dto.ProductOFFDto;
import com.smartpoke.api.common.external.OpenFoodFacts.response.ProductResponseOFF;
import com.smartpoke.api.common.external.OpenFoodFacts.response.OFFResponse;
import com.smartpoke.api.feature.product.model.Product;
import com.smartpoke.api.feature.product.service.ProductService;
import com.squareup.okhttp.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OpenFoodFactsClient {
    private List<String> stores = Arrays.asList("mercadona", "carrefour");
    private Logger logger = LoggerFactory.getLogger(OpenFoodFactsClient.class);

    private final RestTemplate restTemplate = new RestTemplate();
    private final OkHttpClient client = new OkHttpClient();
    @Value("${openfoodfacts.product-url}")
    private String productUrl;
    @Value("${openfoodfacts.filter}")
    private String filter;
    @Value("${openfoodfacts.supermarket-url}")
    private String supermarketUrl;


    public List<Product> syncProducts() throws RuntimeException{
        try {
            List<ProductOFFDto> productsStore = new ArrayList<ProductOFFDto>();
            for (String store : stores){
                productsStore.addAll(fetchProductsFromStore(store));
            }
            List<Product> productsEntity = new ArrayList<>();
            productsStore.forEach( p -> {
                productsEntity.add(p.toEntity());
                logger.info("Created or updated new product: {}", p);
            });
            return productsEntity;
        }catch (Exception e){
            throw new RuntimeException(e);
        }


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
            page++;
        }

        return products;
    }


    public Product fetchProductDetails(String barcode){
        String url = String.format(productUrl, barcode, filter);
        ProductResponseOFF response = restTemplate.getForObject(url, ProductResponseOFF.class);
        if (response == null) throw new ResourceNotFoundException("Product not exist in OpenFoodFacts database");
        Product productEntity = response.getProduct().toEntity();

        //Save product once it's fetch from OpenFoodFacts
        return productEntity;
    }

}
