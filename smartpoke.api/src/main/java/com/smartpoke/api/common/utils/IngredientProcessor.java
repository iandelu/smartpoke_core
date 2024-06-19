package com.smartpoke.api.common.utils;

import com.smartpoke.api.feature.product.model.Product;
import com.smartpoke.api.feature.product.service.ProductService;
import com.smartpoke.api.feature.recipe.model.RecipeProduct;
import com.smartpoke.api.feature.recipe.service.UnitOfMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientProcessor {

    @Autowired
    private UnitOfMeasureService unitOfMeasureService;
    @Autowired
    private ProductService productService;


    private static final Map<String, Double> numberWords = new HashMap<>();
    private static final Map<String, String> unitMappings = new HashMap<>();


    static {
        numberWords.put("uno", 1.0);
        numberWords.put("una", 1.0);
        numberWords.put("dos", 2.0);
        numberWords.put("tres", 3.0);
        numberWords.put("cuatro", 3.0);
        numberWords.put("½", 0.5);
        numberWords.put("1/2", 0.5);
        numberWords.put("⅓", 0.33);
        numberWords.put("1/3", 0.33);
        numberWords.put("¼", 0.25);
        numberWords.put("1/4", 0.25);
        numberWords.put("1/5", 0.25);

        unitMappings.put("g", "gr");
        unitMappings.put("gr", "gr");
        unitMappings.put("gramo", "gr");
        unitMappings.put("gramos", "gr");
        unitMappings.put("kg", "kg");
        unitMappings.put("Kg", "kg");
        unitMappings.put("kilo", "kg");
        unitMappings.put("kilos", "kg");
        unitMappings.put("kilogramos", "kg");
        unitMappings.put("l", "l");
        unitMappings.put("L", "l");
        unitMappings.put("litro", "l");
        unitMappings.put("Litro", "l");
        unitMappings.put("litros", "l");
        unitMappings.put("mililitro", "ml");
        unitMappings.put("ml", "ml");
        unitMappings.put("mL", "ml");
        unitMappings.put("mililitros", "ml");

    }

    public RecipeProduct ingredientTextToProduct(String ingredientStr) {

        //We create a new RecipeIngredient object and set the text raw
        RecipeProduct recipeProduct = new RecipeProduct();
        recipeProduct.setText(ingredientStr);

        String[] tokens = NormalizerUtils.tokenizeIngredient(ingredientStr);

        Double amount = null;
        String unit = null;
        ArrayList<String> ingredientTokens = new ArrayList<>();

        for (String token : tokens) {
            if (numberWords.containsKey(token)) {
                amount = numberWords.get(token);
            } else if (unitMappings.containsKey(token)) {
                unit = unitMappings.get(token);
            } else {
                try {
                    amount = Double.parseDouble(token);
                } catch (NumberFormatException e) {
                    if (!token.isEmpty() && !token.equals("de"))
                        ingredientTokens.add(token);
                }
            }
        }

        //We join the ingredient name
        String ingredientName = String.join(" ", ingredientTokens);
        recipeProduct.setIngredientName(ingredientName);

        //Buscar Producto en la base de datos TODO MEJORAR BUSQUEDA POR SIMILITUD
        Product product = productService.findOrCreateProduct(ingredientName);

        //We set the amount, unit and ingredient
        recipeProduct.setAmount(amount);
        recipeProduct.setUnitOfMeasure(unitOfMeasureService.findOrCreateNewUnitOfMeasure(unit));
        recipeProduct.setProduct(product);

        return recipeProduct;
    }


}
