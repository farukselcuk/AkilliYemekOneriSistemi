package com.faruk.recipeapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipe_ingredients")
@IdClass(RecipeIngredientId.class)
@NoArgsConstructor
@Data
public class RecipeIngredient {

    @Id
    @Column(name = "recipe_id")
    private Long recipeId;

    @Id
    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Column
    private Integer ratio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", insertable = false, updatable = false)
    private Ingredient ingredient;

    // Parametreli constructor
    public RecipeIngredient(Long recipeId, Long ingredientId, Integer ratio, Recipe recipe, Ingredient ingredient) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.ratio = ratio;
        this.recipe = recipe;
        this.ingredient = ingredient;
    }
}