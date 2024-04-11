package com.verise.veganapp.entities.model;

import com.verise.veganapp.utils.MealType;

import java.util.Date;

public record StandardRecipe(String name, String description, String status, double rate, MealType mealType, Date createdDate) {
}
