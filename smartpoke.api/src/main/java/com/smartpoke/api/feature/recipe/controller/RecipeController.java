package com.smartpoke.api.feature.recipe.controller;

import com.smartpoke.api.integrations.RecipeScrapers.dto.UrlDto;
import com.smartpoke.api.feature.recipe.dto.RecipeDto;
import com.smartpoke.api.feature.recipe.dto.RecipeMapper;
import com.smartpoke.api.feature.recipe.model.Recipe;
import com.smartpoke.api.feature.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<Page<RecipeDto>> getAllRecipes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok().body(recipeService.getAllRecipes(page, size));
    }

    @GetMapping("/by_difficult/{difficult}")
    public ResponseEntity<Page<RecipeDto>> getRecipesByDifficult(
            @PathVariable String difficult,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok().body(recipeService.getRecipesByDifficult(page, size, difficult));
    }

    @GetMapping("/by_category/{category}")
    public ResponseEntity<Page<RecipeDto>> getRecipesByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok().body(recipeService.getRecipesByCategory(page, size, category));
    }


    @GetMapping("/search")
    public ResponseEntity<Page<RecipeDto>> searchRecipes(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10")int size) {
        return ResponseEntity.ok().body(recipeService.searchRecipes(page, size, name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipe(@PathVariable Long id){
        return ResponseEntity.ok().body(recipeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RecipeDto> createRecipe(@RequestBody Recipe recipe){
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createRecipe(recipe));
    }

    @PostMapping("from_url")
    public ResponseEntity<RecipeDto> createRecipeFromUrl(@RequestBody UrlDto urlDto){
        RecipeDto recipe = RecipeMapper.toDto(recipeService.createRecipeFromUrl(urlDto.getUrl(), "true"));
        return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
    }

    @GetMapping("load_base")
    public ResponseEntity<List<RecipeDto>> loadRecipeBase(){
        List<RecipeDto> recipes = recipeService.loadRecipeBase();
        return ResponseEntity.status(HttpStatus.CREATED).body(recipes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDto> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok().body(recipeService.updateRecipe(id, recipe));
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}
