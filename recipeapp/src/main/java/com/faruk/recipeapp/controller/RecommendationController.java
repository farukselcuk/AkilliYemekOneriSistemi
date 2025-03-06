package com.faruk.recipeapp.controller;

import com.faruk.recipeapp.dto.RecipeDto;
import com.faruk.recipeapp.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
/api/recommendations/{userId}: Kullanıcının favori tercihlerine göre tarif önerileri getirir.
/api/recommendations/favorite/{userId}/{recipeId}: Kullanıcının bir tarifi favorilerine ekler.
 */

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public List<RecipeDto> getRecommendations(@PathVariable String userId) {
        return recommendationService.suggestRecipesBasedOnPreferences(userId);
    }

    @PostMapping("/favorite/{userId}/{recipeId}")
    public void addFavoriteRecipe(@PathVariable String userId, @PathVariable Long recipeId) {
        recommendationService.addFavoriteRecipe(userId, recipeId);
    }
}