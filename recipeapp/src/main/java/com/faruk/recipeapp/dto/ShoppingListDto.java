package com.faruk.recipeapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListDto {
    private Long id;
    private String userId;
    private List<ShoppingListItemDto> items;
}