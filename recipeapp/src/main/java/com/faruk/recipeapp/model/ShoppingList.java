package com.faruk.recipeapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_lists")
@NoArgsConstructor
@Data
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @OneToMany(mappedBy = "shoppingList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference // Döngüsel referansı yönetmek için
    private List<ShoppingListItem> items;

    // Parametreli constructor
    public ShoppingList(String userId, List<ShoppingListItem> items) {
        this.userId = userId;
        this.items = items != null ? items : new ArrayList<>();
    }
}