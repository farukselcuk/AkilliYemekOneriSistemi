package com.faruk.recipeapp.repository;

import com.faruk.recipeapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("SELECT DISTINCT r FROM Recipe r JOIN r.recipeIngredients ri WHERE ri.ingredient.id IN :ingredientIds")
    List<Recipe> findByIngredients(@Param("ingredientIds") List<Long> ingredientIds);
}