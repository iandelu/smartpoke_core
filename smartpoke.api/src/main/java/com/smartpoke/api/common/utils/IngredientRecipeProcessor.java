package com.smartpoke.api.common.utils;

import com.smartpoke.api.feature.product.model.Product;
import com.smartpoke.api.feature.product.service.ProductService;
import com.smartpoke.api.feature.recipe.model.RecipeProduct;
import com.smartpoke.api.feature.recipe.service.UnitOfMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientRecipeProcessor {

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
        numberWords.put("½", 0.5);
        numberWords.put("1/2", 0.5);
        numberWords.put("⅓", 0.33);
        numberWords.put("1/3", 0.33);
        numberWords.put("¼", 0.25);
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
        recipeProduct.setIngredientName(ingredientStr);

        //We replace the "-" with " " and split the string by spaces
        String[] tokens = ingredientStr.replace("-", " ").split("\\s+");

        Double amount = null;
        String unit = null;
        ArrayList<String> ingredient = new ArrayList<>();

        //We analyze each token
        for (String token : tokens) {
            try {
                //If the token is a number, we set the amount
                amount = Double.parseDouble(token);
            } catch (NumberFormatException e) {
                //If the token is not a number, we check if it is a UNIT Stored in the unitMappings
                if (numberWords.containsKey(token)) {
                    //If the token is a number word, we set the amount as number
                    amount = numberWords.get(token);
                } else if (unitMappings.containsKey(token)) {
                    //If the token is a unit, we set the unit
                    unit = unitMappings.get(token);
                } else {
                    //If the token is not a number or a unit, we assume it as part of the ingredient name
                    ingredient.add(token);
                }
            }
        }

        //We join the ingredient name
        String productName = String.join(" ", ingredient);

        //Buscar Producto en la base de datos normalizar y buscar
        Product product = productService.findOrCreateProduct(productName);

        //We set the amount, unit and ingredient
        recipeProduct.setAmount(amount);
        recipeProduct.setUnitOfMeasure(unitOfMeasureService.createNewUnitOfMeasure(unit));
        recipeProduct.setProduct(productService.saveProduct(product));

        return recipeProduct;
    }
}
