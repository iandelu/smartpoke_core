package com.smartpoke.api.integrations.OpenFoodFacts;

import com.smartpoke.api.common.exceptions.ResourceNotFoundException;
import com.smartpoke.api.integrations.OpenFoodFacts.dto.ProductOFFDto;
import com.smartpoke.api.integrations.OpenFoodFacts.response.ProductResponseOFF;
import com.smartpoke.api.integrations.OpenFoodFacts.response.OFFResponse;
import com.smartpoke.api.feature.product.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class OpenFoodFactsClient {
    private List<String> stores = Arrays.asList("mercadona", "carrefour");
    private final Logger logger = LoggerFactory.getLogger(OpenFoodFactsClient.class);

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${openfoodfacts.product-url}")
    private String productUrl;
    @Value("${openfoodfacts.filter}")
    private String filter;
    @Value("${openfoodfacts.supermarket-url}")
    private String supermarketUrl;


    public List<Product> syncProducts() throws RuntimeException{
        List<CompletableFuture<List<ProductOFFDto>>> futureProducts = stores.stream()
                .map(store -> CompletableFuture.supplyAsync(() -> fetchProductsFromStore(store)))
                .toList();

        List<ProductOFFDto> productsStore = futureProducts.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .toList();

        return productsStore.stream()
                .map(ProductOFFDto::toEntity)
                .peek(product -> logger.info("Created or updated new product: {}", product))
                .collect(Collectors.toList());


    }
    public List<ProductOFFDto> fetchProductsFromStore(String store) {
        List<ProductOFFDto> products = new ArrayList<>();
        int page = 1;
        OFFResponse response;
        do {
            String url = String.format(supermarketUrl, store, filter, page);
            try {
                response = restTemplate.getForObject(url, OFFResponse.class);
                if (response != null && response.getProducts() != null) {
                    products.addAll(response.getProducts());
                    page++;
                } else {
                    // Si la respuesta es nula o no tiene productos, salir del bucle.
                    break;
                }
            } catch (Exception e) {
                logger.error("Error fetching products from store: {}", store, e);
                break;
            }
        } while (!response.getProducts().isEmpty() && page <= response.getPage_size());

        return products;
    }


    public Product fetchProductDetails(String barcode){
        String url = String.format(productUrl, barcode, filter);
        ProductResponseOFF response = restTemplate.getForObject(url, ProductResponseOFF.class);
        if (response == null) throw new ResourceNotFoundException("Product not exist in OpenFoodFacts database");

        return response.getProduct().toEntity();
    }

}
