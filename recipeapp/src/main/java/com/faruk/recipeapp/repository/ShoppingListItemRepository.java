package com.faruk.recipeapp.repository;

import com.faruk.recipeapp.model.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, Long> {
}