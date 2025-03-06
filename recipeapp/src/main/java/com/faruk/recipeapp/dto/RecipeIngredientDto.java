package com.faruk.recipeapp.dto;

public class RecipeIngredientDto {
    private Long recipeId;
    private Long ingredientId;
    private String ingredientName;
    private Integer ratio;

    // Constructor, Getter ve Setter'lar
    public RecipeIngredientDto(Long recipeId, Long ingredientId, String ingredientName, Integer ratio) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ratio = ratio;
    }

    public Long getRecipeId() { return recipeId; }
    public void setRecipeId(Long recipeId) { this.recipeId = recipeId; }
    public Long getIngredientId() { return ingredientId; }
    public void setIngredientId(Long ingredientId) { this.ingredientId = ingredientId; }
    public String getIngredientName() { return ingredientName; }
    public void setIngredientName(String ingredientName) { this.ingredientName = ingredientName; }
    public Integer getRatio() { return ratio; }
    public void setRatio(Integer ratio) { this.ratio = ratio; }
}