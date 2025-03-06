package com.faruk.recipeapp.repository;

import com.faruk.recipeapp.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    Optional<ShoppingList> findByUserId(String userId);
}