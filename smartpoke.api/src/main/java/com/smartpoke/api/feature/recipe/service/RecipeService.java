package com.smartpoke.api.feature.recipe.service;

import com.smartpoke.api.common.exceptions.ResourceNotFoundException;
import com.smartpoke.api.common.utils.IngredientProcessor;
import com.smartpoke.api.feature.category.service.TagService;
import com.smartpoke.api.integrations.RecipeScrapers.RecipeScraperClient;
import com.smartpoke.api.integrations.RecipeScrapers.dto.RecipeScrapDto;
import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.category.repository.CategoryRepository;
import com.smartpoke.api.feature.recipe.dto.RecipeDto;
import com.smartpoke.api.feature.recipe.dto.RecipeMapper;
import com.smartpoke.api.feature.recipe.model.DifficultyEnum;
import com.smartpoke.api.feature.recipe.model.Recipe;
import com.smartpoke.api.feature.recipe.model.RecipeProduct;
import com.smartpoke.api.feature.recipe.repository.RecipeIngredientsRepository;
import com.smartpoke.api.feature.recipe.repository.RecipeRepository;
import com.smartpoke.api.feature.recipe.repository.RecipeStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService implements IRecipeService{
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeIngredientsRepository recipeIngredientsRepository;
    @Autowired
    private RecipeStepRepository recipeStepRepository;
    @Autowired
    private RecipeScraperClient recipeScraperClient;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private IngredientProcessor ingredientProcessor;

    @Override
    public RecipeDto createRecipe(Recipe recipe) {
        return RecipeMapper.toDto(recipeRepository.save(recipe));
    }

    @Override
    public Page<RecipeDto> getAllRecipes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipePage = recipeRepository.findAll(pageable);

        List<RecipeDto> dtoList = recipePage.stream()
                .map(RecipeMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, recipePage.getTotalElements());
    }

    @Override
    public Page<RecipeDto> getRecipesByDifficult(int page, int size, String difficult) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipePage = recipeRepository.findByDifficultyEnum(DifficultyEnum.valueOf(difficult), pageable);

        List<RecipeDto> dtoList = recipePage.stream()
                .map(RecipeMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, recipePage.getTotalElements());
    }

    @Override
    public Page<RecipeDto> searchRecipes(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipePage = recipeRepository.findByNameContaining(name, pageable);

        List<RecipeDto> dtoList = recipePage.stream()
                .map(RecipeMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, recipePage.getTotalElements());
    }

    @Override
    public RecipeDto findById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe not found"));

        return RecipeMapper.toDto(recipe);
    }

    @Override
    public RecipeDto updateRecipe(Long id, Recipe recipe) {
        if (recipeRepository.existsById(id)) {
            recipe.setId(id);
            return RecipeMapper.toDto(recipeRepository.save(recipe));
        } else {
            return null;
        }
    }

    @Override
    public void deleteRecipe(Long id){recipeRepository.deleteById(id);}

    @Override
    public Recipe createRecipeFromUrl(String url) {
        return createRecipeFromUrl(url, "true");
    }
    @Override
    public Recipe createRecipeFromUrl(String url, String wild) {
        try {
            RecipeScrapDto recipeScrapDto = recipeScraperClient.getRecipeScraped(url, wild);
            if (recipeScrapDto!=null){
                Recipe recipe = recipeScrapDto.toEntity();
                recipe.setRecipeProducts(convertIngredientsToProducts(recipeScrapDto.getIngredients()));
                recipe.setCategories(convertCategories(recipeScrapDto.getCategories()));

                return recipeRepository.save(recipe);
            }
            throw new ResourceNotFoundException("Not possible to find this recipe, try later");
        } catch (IOException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    private Set<Category> convertCategories(List<String> categories) {
        Set<Category> categorySet = new HashSet<>();
        if(categories != null && !categories.isEmpty()){
            for (String s : categories) {
                 categorySet.add(getCategory(s));
            }
        }
        return categorySet;
    }

    public Category getCategory(String s) {
        return categoryRepository.findByName(s.toLowerCase())
                .orElseGet(() -> createNewCategory(s));
    }
    private Category createNewCategory(String s) {
        Category category = new Category();
        category.setName(s.toLowerCase());
        category.setLan("es");
        return categoryRepository.save(category);
    }


    @Override
    public List<RecipeDto> createRecipeListFromUrl(List<String> urls) {
        List<Recipe> recipeListEntity = new ArrayList<>();
        urls.forEach( url -> {
            try{
                Recipe newRecipe = createRecipeFromUrl(url);
                recipeListEntity.add(newRecipe);
            }catch (ResourceNotFoundException e) {
                System.out.println(e.getMessage());
            }

        });
        return recipeRepository.saveAll(recipeListEntity).stream()
                .map(RecipeMapper::toDto)
                .toList();
    }

    @Override
    public List<RecipeDto> loadRecipeBase() {
        List<String> urls = recipeScraperClient.loadUrls();
        return createRecipeListFromUrl(urls);

    }

    private Set<RecipeProduct> convertIngredientsToProducts(List<String> recipeIngredientsText) {
        Set<RecipeProduct> recipeProducts = new HashSet<>();
        if(recipeIngredientsText != null && !recipeIngredientsText.isEmpty()){
            for (String s : recipeIngredientsText) {
                recipeProducts.add(ingredientProcessor.ingredientTextToProduct(s));
            }
        }
        return recipeProducts;
    }

}
