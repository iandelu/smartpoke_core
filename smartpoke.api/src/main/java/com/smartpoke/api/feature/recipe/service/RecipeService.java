package com.smartpoke.api.feature.recipe.service;

import com.smartpoke.api.common.exceptions.ResourceNotFoundException;
import com.smartpoke.api.common.external.RecipeScrapers.RecipeScraperClient;
import com.smartpoke.api.common.external.RecipeScrapers.dto.RecipeScrapDto;
import com.smartpoke.api.feature.category.model.Category;
import com.smartpoke.api.feature.category.repository.CategoryRepository;
import com.smartpoke.api.feature.product.model.Ingredient;
import com.smartpoke.api.feature.product.repository.IngredientRepository;
import com.smartpoke.api.feature.recipe.dto.RecipeDto;
import com.smartpoke.api.feature.recipe.dto.RecipeMapper;
import com.smartpoke.api.feature.recipe.model.DifficultyEnum;
import com.smartpoke.api.feature.recipe.model.Recipe;
import com.smartpoke.api.feature.recipe.model.RecipeIngredient;
import com.smartpoke.api.feature.recipe.model.UnitOfMeasure;
import com.smartpoke.api.feature.recipe.repository.RecipeIngredientsRepository;
import com.smartpoke.api.feature.recipe.repository.RecipeRepository;
import com.smartpoke.api.feature.recipe.repository.RecipeStepRepository;
import com.smartpoke.api.feature.recipe.repository.UnitOfMesureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private IngredientRepository ingredientRepository;
    @Autowired
    private UnitOfMesureRepository unitOfMesureRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private static final Map<String, Double> numberWords = new HashMap<>();
    private static final Map<String, String> unitMappings = new HashMap<>();

    static {
        numberWords.put("uno", 1.0);
        numberWords.put("una", 1.0);
        numberWords.put("dos", 2.0);
        numberWords.put("tres", 3.0);
        numberWords.put("½", 0.5);
        numberWords.put("¼", 0.25);

        unitMappings.put("g", "gr");
        unitMappings.put("gr", "gr");
        unitMappings.put("gramo", "gr");
        unitMappings.put("gramos", "gr");
        unitMappings.put("kg", "kg");
        unitMappings.put("kilo", "kg");
        unitMappings.put("kilos", "kg");
        unitMappings.put("kilogramos", "kg");
        unitMappings.put("l", "l");
        unitMappings.put("litro", "l");
        unitMappings.put("litros", "l");
        unitMappings.put("mililitro", "ml");
        unitMappings.put("ml", "ml");
        unitMappings.put("mL", "ml");
        unitMappings.put("mililitros", "ml");

    }

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
    public Recipe createRecipeFromUrl(String url, String wild) {
        try {
            RecipeScrapDto recipeScrapDto = recipeScraperClient.getRecipeScraped(url, wild);
            if (recipeScrapDto!=null){
                Recipe recipe = recipeScrapDto.toEntity();
                recipe.setRecipeIngredients(convertIngredients(recipeScrapDto.getIngredients()));
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

    public Recipe createRecipeFromUrl(String url) {
        return createRecipeFromUrl(url, "true");
    }

    @Override
    public List<Recipe> createRecipeListFromUrl(List<String> urls) {
        List<Recipe> recipeListEntity = new ArrayList<>();
        urls.forEach( url -> {
            Recipe newRecipe = createRecipeFromUrl(url);
            recipeListEntity.add(newRecipe);
        });
        return recipeRepository.saveAll(recipeListEntity);
    }

    @Override
    public List<RecipeDto> loadRecipeBase() {
        List<String> urls = RecipeScraperClient.loadUrls();
        return createRecipeListFromUrl(urls)
                .stream()
                .map(RecipeMapper::toDto)
                .collect(Collectors.toList());
    }

    private Set<RecipeIngredient> convertIngredients(List<String> recipeIngredientsText) {
        Set<RecipeIngredient> recipeIngredients = new HashSet<>();
        if(recipeIngredientsText != null && !recipeIngredientsText.isEmpty()){
            for (String s : recipeIngredientsText) {
                recipeIngredients.add(textToIngredient(s));
            }
        }
        return recipeIngredients;
    }

    private RecipeIngredient textToIngredient(String ingredientStr) {
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientText(ingredientStr);

        String[] tokens = ingredientStr.replace("-", " ").split("\\s+");

        Double amount = null;
        String unit = null;
        ArrayList<String> ingredient = new ArrayList<>();

        for (String token : tokens) {
            try {
                amount = Double.parseDouble(token);
            } catch (NumberFormatException e) {
                if (numberWords.containsKey(token)) {
                    // handle valid amounts as strings, or convert them to numbers as needed
                } else if (unitMappings.containsKey(token)) {
                    unit = unitMappings.get(token);
                } else {
                    ingredient.add(token);
                }
            }
        }

        String ingredientName = String.join(" ", ingredient);

        recipeIngredient.setAmount(amount);
        recipeIngredient.setUnitOfMeasure(getUnitOfMeasure(unit));
        recipeIngredient.setIngredient(getIngredient(ingredientName));

        return recipeIngredient;
    }

    private UnitOfMeasure getUnitOfMeasure(String unit) {
        return unitOfMesureRepository.findByName(unit)
                .orElseGet(() -> createNewUnit(unit));
    }

    private Ingredient getIngredient(String ingredientName) {
        return ingredientRepository.findByName(ingredientName)
                .orElseGet(() -> createNewIngredient(ingredientName));
    }


    private UnitOfMeasure createNewUnit(String unit) {
        UnitOfMeasure unitOfMeasure= new UnitOfMeasure();
        unitOfMeasure.setName(unit);

        return unitOfMesureRepository.save(unitOfMeasure);
    }

    private Ingredient createNewIngredient(String ingredientText) {
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName(ingredientText);
        newIngredient.setLanguage("es");
        return ingredientRepository.save(newIngredient);
    }


}
