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

    private static final Map<String, Integer> numberWords = new HashMap<>();
    private static final Map<String, String> unitMappings = new HashMap<>();

    static {
        numberWords.put("uno", 1);
        numberWords.put("dos", 2);
        numberWords.put("tres", 3);

        unitMappings.put("g", "gramo");
        unitMappings.put("gramo", "gramo");
        unitMappings.put("gramos", "gramos");
        unitMappings.put("kg", "kilogramo");
        unitMappings.put("kilo", "kilogramo");
        unitMappings.put("kilos", "kilogramos");
        unitMappings.put("kilogramos", "kilogramos");
        unitMappings.put("l", "litro");
        unitMappings.put("litro", "litro");
        unitMappings.put("litros", "litro");
        unitMappings.put("mililitro", "mililitro");
        unitMappings.put("mililitros", "mililitros");
        unitMappings.put("pizca", "pizca");
        unitMappings.put("puñado", "puñado");
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
        return categoryRepository.findByName(s)
                .orElseGet(() -> createNewCategory(s));
    }
    private Category createNewCategory(String s) {
        Category category = new Category();
        category.setName(s);
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

    private RecipeIngredient textToIngredient(String text) {
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientText(text);

        Pattern pattern = Pattern.compile("(\\w+|\\d+\\.?\\d*)\\s*(\\w+)\\s*(.+)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            double amount = parseAmount(matcher.group(1));
            String unit = normalizeUnit(matcher.group(2));
            String ingredient;

            if (unit.equals("")){
                ingredient = matcher.group(2) + " " + matcher.group(3);
            }else{
                ingredient = matcher.group(3);
            }

            recipeIngredient.setAmount(amount);
            recipeIngredient.setUnitOfMeasure(getUnitOfMeasure(unit));
            recipeIngredient.setIngredient(getIngredient(ingredient));

        } else {
            throw new IllegalArgumentException("Formato de texto no reconocido");
        }

        return recipeIngredient;
    }

    private double parseAmount(String amountStr) {
        if (numberWords.containsKey(amountStr.toLowerCase())) {
            return numberWords.get(amountStr.toLowerCase());
        } else {
            try {
                return Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                return 1.0;
            }
        }
    }

    private String normalizeUnit(String unit) {
        return unitMappings.getOrDefault(unit.toLowerCase(), "");
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
