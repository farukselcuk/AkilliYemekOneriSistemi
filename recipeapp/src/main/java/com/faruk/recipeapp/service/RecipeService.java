package com.faruk.recipeapp.service;

import com.faruk.recipeapp.dto.IngredientWithRatioDto;
import com.faruk.recipeapp.dto.RecipeDto;
import com.faruk.recipeapp.dto.RecipeIngredientDto;
import com.faruk.recipeapp.model.Ingredient;
import com.faruk.recipeapp.model.Recipe;
import com.faruk.recipeapp.model.RecipeIngredient;
import com.faruk.recipeapp.repository.IngredientRepository;
import com.faruk.recipeapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<RecipeDto> suggestRecipes(List<IngredientWithRatioDto> userIngredients) {
        if (userIngredients == null || userIngredients.isEmpty()) {
            return List.of();
        }

        for (IngredientWithRatioDto ingredientDto : userIngredients) {
            String ingredientName = ingredientDto.getName();
            if (!ingredientRepository.existsByName(ingredientName)) {
                Ingredient ingredient = new Ingredient(ingredientName);
                ingredientRepository.save(ingredient);
            }
        }

        List<Ingredient> dbIngredients = userIngredients.stream()
                .map(ingredient -> ingredientRepository.findByName(ingredient.getName()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (dbIngredients.isEmpty()) {
            return List.of();
        }

        List<Long> ingredientIds = dbIngredients.stream()
                .map(Ingredient::getId)
                .collect(Collectors.toList());

        return recipeRepository.findByIngredients(ingredientIds).stream()
                .filter(recipe -> {
                    double matchScore = recipe.getRecipeIngredients().stream()
                            .filter(ri -> ingredientIds.contains(ri.getIngredient().getId()))
                            .mapToDouble(ri -> {
                                Integer userRatio = userIngredients.stream()
                                        .filter(ui -> ui.getName().equals(ri.getIngredient().getName()))
                                        .map(IngredientWithRatioDto::getRatio)
                                        .findFirst()
                                        .orElse(0);
                                return Math.min(ri.getRatio() != null ? ri.getRatio() : 0, userRatio);
                            })
                            .average()
                            .orElse(0.0);
                    return matchScore > 10;
                })
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public RecipeDto convertToDto(Recipe recipe) {
        List<RecipeIngredientDto> ingredientDtos = recipe.getRecipeIngredients().stream()
                .map(ri -> new RecipeIngredientDto(
                        ri.getRecipeId(),
                        ri.getIngredientId(),
                        ri.getIngredient().getName(),
                        ri.getRatio()
                ))
                .collect(Collectors.toList());

        return new RecipeDto(
                recipe.getId(),
                recipe.getName(),
                recipe.getInstructions(),
                ingredientDtos
        );
    }
}