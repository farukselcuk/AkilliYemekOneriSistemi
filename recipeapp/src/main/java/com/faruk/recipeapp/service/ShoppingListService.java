package com.faruk.recipeapp.service;

import com.faruk.recipeapp.dto.ShoppingListDto;
import com.faruk.recipeapp.dto.ShoppingListItemDto;
import com.faruk.recipeapp.model.*;
import com.faruk.recipeapp.repository.IngredientRepository;
import com.faruk.recipeapp.repository.RecipeRepository;
import com.faruk.recipeapp.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public ShoppingListDto addRecipeToShoppingList(String userId, Long recipeId) {
        ShoppingList shoppingList = shoppingListRepository.findByUserId(userId)
                .orElseGet(() -> {
                    ShoppingList newList = new ShoppingList(userId, null);
                    return shoppingListRepository.save(newList);
                });

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Tarif bulunamadı: " + recipeId));

        // Mevcut öğeleri ingredientId’ye göre haritala
        Map<Long, ShoppingListItem> itemMap = shoppingList.getItems().stream()
                .collect(Collectors.toMap(
                        item -> item.getIngredient().getId(),
                        item -> item
                ));

        // Tarifin malzemelerini ekle veya miktarları güncelle
        for (RecipeIngredient ri : recipe.getRecipeIngredients()) {
            Long ingredientId = ri.getIngredient().getId();
            ShoppingListItem existingItem = itemMap.get(ingredientId);
            if (existingItem != null) {
                // Mevcut öğe varsa, miktarı artır
                existingItem.setQuantity(existingItem.getQuantity() + ri.getRatio());
            } else {
                // Yeni öğe ekle
                ShoppingListItem newItem = new ShoppingListItem(shoppingList, ri.getIngredient(), ri.getRatio());
                shoppingList.getItems().add(newItem);
                itemMap.put(ingredientId, newItem);
            }
        }

        ShoppingList savedList = shoppingListRepository.save(shoppingList);

        // DTO’ya dönüştür
        return convertToDto(savedList);
    }

    private ShoppingListDto convertToDto(ShoppingList shoppingList) {
        List<ShoppingListItemDto> itemDtos = shoppingList.getItems().stream()
                .map(item -> new ShoppingListItemDto(
                        item.getId(),
                        item.getIngredient().getId(),
                        item.getIngredient().getName(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());

        return new ShoppingListDto(
                shoppingList.getId(),
                shoppingList.getUserId(),
                itemDtos
        );
    }
}