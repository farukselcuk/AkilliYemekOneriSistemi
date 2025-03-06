package com.faruk.recipeapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shopping_list_items")
@NoArgsConstructor
@Data
public class ShoppingListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_list_id")
    @JsonBackReference // Döngüsel referansı önlemek için
    private ShoppingList shoppingList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(nullable = false)
    private Integer quantity;

    // Parametreli constructor
    public ShoppingListItem(ShoppingList shoppingList, Ingredient ingredient, Integer quantity) {
        this.shoppingList = shoppingList;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }
}