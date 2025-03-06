package com.faruk.recipeapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
@NoArgsConstructor
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String instructions;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference // JSON serileştirme döngüsünü önlemek için
    private List<RecipeIngredient> recipeIngredients;

    // Parametreli constructor
    public Recipe(String name, String instructions, List<RecipeIngredient> recipeIngredients) {
        this.name = name;
        this.instructions = instructions;
        this.recipeIngredients = recipeIngredients != null ? recipeIngredients : new ArrayList<>();
    }
}