package com.verise.veganapp.service;

import com.verise.veganapp.entities.Recipe;
import com.verise.veganapp.repository.IngredientRepository;
import com.verise.veganapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    RecipeRepository recipeRepository;
    IngredientRepository ingredientRepository;
    @Autowired
    public RecipeService(RecipeRepository recipeRepository,IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository=ingredientRepository;
    }
    /*
     show all the available recipes based upon recipe type
     */
    public List<Recipe> fetchAvailableRecipes()
    {
        List<Recipe> recipes=recipeRepository.fetchAllAvailableRecipes();
        if(recipes!=null)
          return recipes;
        return new ArrayList<>();
    }

}
