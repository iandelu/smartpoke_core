package com.smartpoke.api.common.model;


import com.smartpoke.api.feature.product.model.ProductNutrients;
import com.smartpoke.api.feature.recipe.model.NutrientsRecipe;
import lombok.Data;

@Data
public class Nutrients {

    private Integer id;
    private Integer amount;
    private Integer calories;
    private Double fats;
    private Double saturatedFats;
    private Double cholesterol;
    private Double cabs;
    private Double fiber;
    private Double protein;
    private Double salt;

    // MicroNutrients
    private Double alcohol;  // grams
    private Double water;     // grams
    private Double caffeine;  // milligrams
    private Double sugars;    // grams
    private Double calcium;   // milligrams
    private Double iron;     // milligrams
    private Double magnesium; // milligrams
    private Double phosphorus;// milligrams
    private Double potassium; // milligrams
    private Double sodium;    // milligrams
    private Double zinc;      // milligrams
    private Double copper;    // milligrams
    private Double retinol;   // micrograms
    private Double vitaminA;  // micrograms RAE
    private Double vitaminE;  // milligrams
    private Double vitaminD;  // micrograms
    private Double vitaminC;  // milligrams
    private Double vitaminB6; // milligrams
    private Double vitaminB12;// micrograms
    private Double vitaminK;  // micrograms
    private Double thiamin;   // milligrams
    private Double niacin;    // milligrams


    public Nutrients(NutrientsRecipe nutrientsRecipe){
        this.amount = nutrientsRecipe.getAmount();
        this.calories = nutrientsRecipe.getCalories();
        this.fats = nutrientsRecipe.getFats();
        this.saturatedFats = nutrientsRecipe.getSaturatedFats();
        this.cholesterol = nutrientsRecipe.getCholesterol();
        this.cabs = nutrientsRecipe.getCabs();
        this.fiber = nutrientsRecipe.getFiber();
        this.protein = nutrientsRecipe.getProtein();
        this.salt = nutrientsRecipe.getSalt();
        this.sugars = nutrientsRecipe.getSugar();
    }

    public Nutrients(ProductNutrients productNutrients){
        this.amount = productNutrients.getAmount();
        this.calories = productNutrients.getCalories();
        this.fats = productNutrients.getFats();
        this.saturatedFats = productNutrients.getSaturatedFats();
        this.cabs = productNutrients.getCabs();
        this.fiber = productNutrients.getFibre();
        this.protein = productNutrients.getProtein();
        this.salt = productNutrients.getSalt();
        this.alcohol = productNutrients.getAlcohol();
        this.water = productNutrients.getWater();
        this.caffeine = productNutrients.getCaffeine();
        this.sugars = productNutrients.getSugars();
        this.calcium = productNutrients.getCalcium();
        this.iron = productNutrients.getIron();
        this.magnesium = productNutrients.getMagnesium();
        this.phosphorus = productNutrients.getPhosphorus();
        this.potassium = productNutrients.getPotassium();
        this.sodium = productNutrients.getSodium();
        this.zinc = productNutrients.getZinc();
        this.copper = productNutrients.getCopper();
        this.retinol = productNutrients.getRetinol();
        this.vitaminA = productNutrients.getVitaminA();
        this.vitaminE = productNutrients.getVitaminE();
        this.vitaminD = productNutrients.getVitaminD();
        this.vitaminC = productNutrients.getVitaminC();
        this.vitaminB6 = productNutrients.getVitaminB6();
        this.vitaminB12 = productNutrients.getVitaminB12();
        this.vitaminK = productNutrients.getVitaminK();
        this.thiamin = productNutrients.getThiamin();
        this.niacin = productNutrients.getNiacin();
    }

    public NutrientsRecipe toNutrientsRecipe() {
        NutrientsRecipe nutrients = new NutrientsRecipe();
        nutrients.setAmount(this.amount);
        nutrients.setCalories(this.calories);
        nutrients.setFats(this.fats);
        nutrients.setSaturatedFats(this.saturatedFats);
        nutrients.setCholesterol(this.cholesterol);
        nutrients.setCabs(this.cabs);
        nutrients.setFiber(this.fiber);
        nutrients.setProtein(this.protein);
        nutrients.setSalt(this.salt);
        nutrients.setSugar(this.sugars);

        return nutrients;
    }

    public ProductNutrients toNutrientsProduct() {
        ProductNutrients nutrients = new ProductNutrients();
        nutrients.setAmount(this.amount);
        nutrients.setCalories(this.calories);
        nutrients.setFats(this.fats);
        nutrients.setSaturatedFats(this.saturatedFats);
        nutrients.setCabs(this.cabs);
        nutrients.setFibre(this.fiber);
        nutrients.setProtein(this.protein);
        nutrients.setSalt(this.salt);
        nutrients.setAlcohol(this.alcohol);
        nutrients.setWater(this.water);
        nutrients.setCaffeine(this.caffeine);
        nutrients.setSugars(this.sugars);
        nutrients.setCalcium(this.calcium);
        nutrients.setIron(this.iron);
        nutrients.setMagnesium(this.magnesium);
        nutrients.setPhosphorus(this.phosphorus);
        nutrients.setPotassium(this.potassium);
        nutrients.setSodium(this.sodium);
        nutrients.setZinc(this.zinc);
        nutrients.setCopper(this.copper);
        nutrients.setRetinol(this.retinol);
        nutrients.setVitaminA(this.vitaminA);
        nutrients.setVitaminE(this.vitaminE);
        nutrients.setVitaminD(this.vitaminD);
        nutrients.setVitaminC(this.vitaminC);
        nutrients.setVitaminB6(this.vitaminB6);
        nutrients.setVitaminB12(this.vitaminB12);
        nutrients.setVitaminK(this.vitaminK);
        nutrients.setThiamin(this.thiamin);
        nutrients.setNiacin(this.niacin);

        return nutrients;
    }
}
