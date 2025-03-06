package com.faruk.recipeapp.service;

import com.faruk.recipeapp.model.Ingredient;
import com.faruk.recipeapp.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public void addIngredientIfNotExists(String name) {
        if (!ingredientRepository.existsByName(name)) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(name);
            ingredientRepository.save(ingredient);
        }
    }
}