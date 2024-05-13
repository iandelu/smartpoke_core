package com.smartpoke.api.integrations.RecipeScrapers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlDto {
    String url;
    Boolean wild;

}
