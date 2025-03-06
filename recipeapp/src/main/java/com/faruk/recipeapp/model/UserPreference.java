package com.faruk.recipeapp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
UserPreference, bir kullanıcının favori tariflerini (favoriteRecipes) saklar.
userId, kullanıcının kimliğini temsil eder (JWT ile kimlik doğrulama yapıldığında
gerçek bir kullanıcı ID’sine bağlanabilir).
 */
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user_preferences")
@NoArgsConstructor
@Data
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @ManyToMany
    @JoinTable(
            name = "user_preference_recipes",
            joinColumns = @JoinColumn(name = "user_preference_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> favoriteRecipes;

    // Parametreli constructor
    public UserPreference(String userId, List<Recipe> favoriteRecipes) {
        this.userId = userId;
        this.favoriteRecipes = favoriteRecipes != null ? favoriteRecipes : new ArrayList<>();
    }
}