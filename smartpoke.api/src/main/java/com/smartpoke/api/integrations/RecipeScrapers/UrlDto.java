package com.smartpoke.api.integrations.RecipeScrapers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlDto {
    String url;
    Boolean wild;

}
