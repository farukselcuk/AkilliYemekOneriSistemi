package com.faruk.recipeapp.service;

import com.faruk.recipeapp.dto.RecipeDto;
import com.faruk.recipeapp.model.Ingredient;
import com.faruk.recipeapp.model.Recipe;
import com.faruk.recipeapp.model.RecipeIngredient;
import com.faruk.recipeapp.model.UserPreference;
import com.faruk.recipeapp.repository.IngredientRepository;
import com.faruk.recipeapp.repository.RecipeRepository;
import com.faruk.recipeapp.repository.UserPreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RecommendationService {

    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeService recipeService;

    public List<RecipeDto> suggestRecipesBasedOnPreferences(String userId) {
        UserPreference userPreference = userPreferenceRepository.findByUserId(userId);
        if (userPreference == null || userPreference.getFavoriteRecipes().isEmpty()) {
            return List.of();
        }

        List<Ingredient> favoriteIngredients = userPreference.getFavoriteRecipes().stream()
                .flatMap(recipe -> {
                    List<RecipeIngredient> ingredients = recipe.getRecipeIngredients();
                    return ingredients != null ? ingredients.stream() : Stream.empty();
                })
                .map(RecipeIngredient::getIngredient)
                .distinct()
                .collect(Collectors.toList());

        List<Recipe> similarRecipes = recipeRepository.findAll().stream()
                .filter(recipe -> {
                    List<RecipeIngredient> ingredients = recipe.getRecipeIngredients();
                    return ingredients != null && ingredients.stream()
                            .anyMatch(ri -> favoriteIngredients.stream()
                                    .anyMatch(fi -> fi.getId().equals(ri.getIngredient().getId())));
                })
                .collect(Collectors.toList());

        return similarRecipes.stream()
                .map(recipeService::convertToDto)
                .collect(Collectors.toList());
    }

    public void addFavoriteRecipe(String userId, Long recipeId) {
        UserPreference userPreference = userPreferenceRepository.findByUserId(userId);
        if (userPreference == null) {
            userPreference = new UserPreference(userId, new ArrayList<>());
        }

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Tarif bulunamadı: " + recipeId));
        userPreference.getFavoriteRecipes().add(recipe);
        userPreferenceRepository.save(userPreference);
    }
}