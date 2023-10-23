package com.smartpoke.api.controller;

import com.smartpoke.api.model.recipes.Recipe;
import com.smartpoke.api.model.users.User;
import com.smartpoke.api.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Set<Recipe>> getAllRecipes(){
        return ResponseEntity.ok().body(recipeService.getAllRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id){
        return ResponseEntity.ok().body(recipeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe){
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createRecipe(recipe));
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
