package com.faruk.recipeapp.dto;

import java.util.List;

public class RecipeDto {
    private Long id;
    private String name;
    private String instructions;
    private List<RecipeIngredientDto> ingredients;

    // Constructor, Getter ve Setter'lar
    public RecipeDto(Long id, String name, String instructions, List<RecipeIngredientDto> ingredients) {
        this.id = id;
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
    public List<RecipeIngredientDto> getIngredients() { return ingredients; }
    public void setIngredients(List<RecipeIngredientDto> ingredients) { this.ingredients = ingredients; }
}