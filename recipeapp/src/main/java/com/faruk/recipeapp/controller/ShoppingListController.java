package com.faruk.recipeapp.controller;

import com.faruk.recipeapp.dto.ShoppingListDto;
import com.faruk.recipeapp.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

    @PostMapping("/add-recipe/{userId}/{recipeId}")
    public ResponseEntity<ShoppingListDto> addRecipeToShoppingList(
            @PathVariable String userId,
            @PathVariable Long recipeId) {
        ShoppingListDto shoppingListDto = shoppingListService.addRecipeToShoppingList(userId, recipeId);
        return ResponseEntity.ok(shoppingListDto);
    }
}