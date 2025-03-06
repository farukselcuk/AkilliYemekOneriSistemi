package com.faruk.recipeapp.service;

import com.faruk.recipeapp.dto.IngredientWithRatioDto;
import com.faruk.recipeapp.dto.RecipeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class RecipeServiceTest {

    @Autowired
    private RecipeService recipeService;

    @Test
    public void testSuggestRecipes() {
        List<IngredientWithRatioDto> ingredients = Arrays.asList(
                new IngredientWithRatioDto("sucuk", 50),
                new IngredientWithRatioDto("mısır", 30)
        );
        List<RecipeDto> suggestedRecipes = recipeService.suggestRecipes(ingredients);
        System.out.println("Suggested Recipes: " + suggestedRecipes);
        assertFalse(suggestedRecipes.isEmpty(), "Tarif önerisi alınmalı");
    }
}