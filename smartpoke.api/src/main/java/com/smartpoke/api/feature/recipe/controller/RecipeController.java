package com.smartpoke.api.feature.recipe.controller;

import com.smartpoke.api.common.external.RecipeScrapers.UrlDto;
import com.smartpoke.api.feature.recipe.model.Recipe;
import com.smartpoke.api.feature.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<Page<Recipe>> getAllRecipes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok().body(recipeService.getAllRecipes(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id){
        return ResponseEntity.ok().body(recipeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe){
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createRecipe(recipe));
    }

    @PostMapping("fromUrl")
    public ResponseEntity<Recipe> createRecipeFromUrl(@RequestBody UrlDto urlDto){
        Recipe recipe = recipeService.createRecipeFromUrl(urlDto.getUrl(), "true");
        return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
    }

    @GetMapping("loadBase")
    public ResponseEntity<List<Recipe>> loadRecipeBase(){
        List<Recipe> recipe = recipeService.loadRecipeBase();
        return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateUser(@PathVariable Long id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok().body(recipeService.updateRecipe(id, recipe));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}
