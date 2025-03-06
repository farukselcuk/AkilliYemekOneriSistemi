package com.faruk.recipeapp.repository;

import com.faruk.recipeapp.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
    UserPreference findByUserId(String userId);
}