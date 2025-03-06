package com.faruk.recipeapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ingredients")
@NoArgsConstructor
@Data
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Parametreli constructor
    public Ingredient(String name) {
        this.name = name;
    }
}