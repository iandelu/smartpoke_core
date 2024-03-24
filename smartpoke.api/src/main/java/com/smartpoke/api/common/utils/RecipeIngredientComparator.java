package com.smartpoke.api.common.utils;

import com.smartpoke.api.feature.recipe.model.RecipeProduct;

import java.util.Comparator;

public class RecipeIngredientComparator implements Comparator<RecipeProduct> {
    @Override
    public int compare(RecipeProduct o1, RecipeProduct o2) {
        boolean o1Full = o1.getAmount() != null && o1.getUnitOfMeasure() != null;
        boolean o2Full = o2.getAmount() != null && o2.getUnitOfMeasure() != null;

        if (o1Full && !o2Full) {
            return -1;
        } else if (!o1Full && o2Full) {
            return 1;
        }

        boolean o1AmountOnly = o1.getAmount() != null;
        boolean o2AmountOnly = o2.getAmount() != null;

        if (o1AmountOnly && !o2AmountOnly) {
            return -1;
        } else if (!o1AmountOnly && o2AmountOnly) {
            return 1;
        }

        return o1.getIngredientName().compareToIgnoreCase(o2.getIngredientName());
    }
}