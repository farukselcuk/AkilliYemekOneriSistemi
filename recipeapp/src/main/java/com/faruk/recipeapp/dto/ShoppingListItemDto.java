package com.faruk.recipeapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListItemDto {
    private Long id;
    private Long ingredientId;
    private String ingredientName;
    private Integer quantity;
}