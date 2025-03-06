package com.faruk.recipeapp.dto;

public class IngredientDto {
    private Long id;
    private String name;

    // Varsayılan constructor
    public IngredientDto() {}

    // Parametreli constructor
    public IngredientDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter ve Setter'lar
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}