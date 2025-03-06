package com.faruk.recipeapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class RecipeIngredientId implements Serializable {

    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    // Parametreli constructor
    public RecipeIngredientId(Long recipeId, Long ingredientId) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    // equals ve hashCode Lombok tarafından otomatik oluşturuluyor (@Data sayesinde)
}