package com.faruk.recipeapp.controller;

import com.faruk.recipeapp.dto.IngredientWithRatioDto;
import com.faruk.recipeapp.dto.RecipeDto;
import com.faruk.recipeapp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/suggest")
    public List<RecipeDto> suggestRecipes(@RequestBody List<IngredientWithRatioDto> ingredients) {
        return recipeService.suggestRecipes(ingredients);
    }
}