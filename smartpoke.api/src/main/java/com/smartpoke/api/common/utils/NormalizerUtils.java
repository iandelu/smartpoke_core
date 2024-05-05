package com.smartpoke.api.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class NormalizerUtils {

    public static String[] tokenizeIngredient(String ingredientStr) {
        String[] tokens = ingredientStr.replace("-", " ").split("\\s+");
        return Arrays.stream(tokens).map(NormalizerUtils::normalizeIngredient).toArray(String[]::new);
    }

    private static String normalizeIngredient(String ingredientStr) {
        //We normalize the ingredient text
        String normalizedIngredient = NormalizerUtils.normalize(ingredientStr);
        //We singularize the ingredient text
        return SingularizerUtils.toSingular(normalizedIngredient);
    }

    public static String normalize(String input) {
        // Convert to lower case
        String lowerCase = input.toLowerCase();

        // Delete accents
        String noAccents = StringUtils.stripAccents(lowerCase);

        // Delete special characters
        return noAccents.replaceAll("[^a-zA-Z0-9\\s]", "");
    }
}
