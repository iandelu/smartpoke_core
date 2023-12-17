package com.smartpoke.api.common.external.RecipeScrapers;

import com.smartpoke.api.common.external.RecipeScrapers.dto.RecipeScrapDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class RecipeScraperClient {

    @Value("${recipeScraper.server-url}")
    private String serverUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public RecipeScrapDto getRecipeScraped(String recipeUrl, String wild) throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        UrlDto urlDto = new UrlDto(recipeUrl, Boolean.parseBoolean(wild));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UrlDto> request = new HttpEntity<>(urlDto, headers);

        ResponseEntity<RecipeScrapDto> recipeResponse = restTemplate.postForEntity(serverUrl, request, RecipeScrapDto.class);

        return recipeResponse.getBody();
    }
}