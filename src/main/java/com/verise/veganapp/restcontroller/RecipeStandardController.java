package com.verise.veganapp.restcontroller;

import com.verise.veganapp.entities.Recipe;
import com.verise.veganapp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes/")
public class RecipeStandardController {
    RecipeService recipeService;

    @Autowired
    public RecipeStandardController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    //////fetch all available recipes
    @GetMapping("all-available-recipes")
    public ResponseEntity<List<Recipe>> fetchAllAvailableRecipes()
    {
        return new ResponseEntity<List<Recipe>>(recipeService.fetchAvailableRecipes(), HttpStatus.OK);
    }
}
