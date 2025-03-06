package com.faruk.recipeapp.repository;

import com.faruk.recipeapp.model.RecipeIngredient;
import com.faruk.recipeapp.model.RecipeIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, RecipeIngredientId> {
    List<RecipeIngredient> findAllByRecipeId(Long recipeId);
}