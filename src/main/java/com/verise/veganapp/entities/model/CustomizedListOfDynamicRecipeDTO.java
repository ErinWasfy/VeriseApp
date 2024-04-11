package com.verise.veganapp.entities.model;

import java.util.Date;

public record CustomizedListOfDynamicRecipeDTO(Long id,String recipeName, String recipeDescription, int recipeRate, String recipeStatus, String recipeMealType,
                                               Date visitedDate) {
}
