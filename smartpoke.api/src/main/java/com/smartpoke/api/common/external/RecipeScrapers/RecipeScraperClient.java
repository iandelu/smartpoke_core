package com.smartpoke.api.common.external.RecipeScrapers;

import com.smartpoke.api.common.exceptions.ResourceNotFoundException;
import com.smartpoke.api.common.external.RecipeScrapers.dto.RecipeScrapDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class RecipeScraperClient {

    @Value("${recipeScraper.server-url}")
    private String serverUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public RecipeScrapDto getRecipeScraped(String recipeUrl) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = "{\"url\": \"" + recipeUrl + "\", \"wild\": true}";

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        try {
            ResponseEntity<RecipeScrapDto> response = restTemplate.postForEntity(serverUrl, request, RecipeScrapDto.class);
            if (response.getBody() == null) {
                throw new ResourceNotFoundException("Resource not found");
            }
            return response.getBody();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            throw new IOException("Error communicating with the server");
        }
    }
}