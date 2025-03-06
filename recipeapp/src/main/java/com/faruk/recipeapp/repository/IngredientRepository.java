package com.faruk.recipeapp.repository;

import com.faruk.recipeapp.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByName(String name); // Mevcut
    boolean existsByName(String name); // Yeni metod
}