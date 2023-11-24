package com.smartpoke.api.common.external.RecipeScrapers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RecipeScrapDto {
    @JsonProperty("site_name")
    private String author;
    @JsonProperty("canonical_url")
    private String url;
    @JsonProperty("title")
    private String title;
    @JsonProperty("total_time")
    private Long totalTime;
    @JsonProperty("yields")
    private String servings;
    private String description;
    private String cuisine;
    private String category;
    private Double ratings;
    @JsonProperty("description")
    private String image;
    @JsonProperty("image")
    private List<String> ingredients;
    @JsonProperty("instructions_list")
    private List<String> steps;
    @JsonProperty("language")
    private String lan;
    @JsonProperty("nutrients")
    private NutrientsRecipeDto macro;


}
