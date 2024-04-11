package com.verise.veganapp.restcontroller;

import com.verise.veganapp.entities.Recipe;
import com.verise.veganapp.entities.model.CustomizedListOfDynamicRecipeDTO;
import com.verise.veganapp.entities.model.SuggestedRecipeDTO;
import com.verise.veganapp.service.CustomizedRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customizedRecipes")
public class SuggestedRecipeController {

    CustomizedRecipeService customizedRecipeService;

    @Autowired
    public SuggestedRecipeController(CustomizedRecipeService customizedRecipeService) {
        this.customizedRecipeService = customizedRecipeService;
    }

    @GetMapping("/fetchRecipesByMeal")
    public ResponseEntity<List<CustomizedListOfDynamicRecipeDTO>> fetchDailySuggestedRecipesByMealType(@RequestParam("mealType") String mealType)
    {
        return new ResponseEntity<List<CustomizedListOfDynamicRecipeDTO>>(customizedRecipeService.fetchDynamicDailyRecipesBasedOnMealType(mealType), HttpStatus.OK);
    }

    @GetMapping("/fetchDynamicSuggestedRecipe")
     public ResponseEntity<SuggestedRecipeDTO> fetchSuggestedRecipe()
    {
        return new ResponseEntity<SuggestedRecipeDTO>(customizedRecipeService.fetchSuggestedRecipe(),HttpStatus.OK);
    }

    @GetMapping("/calculatedWeeklyCaloriesByRecipe")
    public ResponseEntity<Double> showCalculatedWeeklyCaloriesByRecipe(@RequestParam String recipeName)
    {
        return new ResponseEntity<Double>(customizedRecipeService.calculateCaloriesOnWeeklyBasisByRecipe(recipeName),HttpStatus.OK);
    }

    @GetMapping("/calculatedDailyCaloriesByRecipe")
    public ResponseEntity<Double> showCalculatedDailyCaloriesByRecipe(@RequestParam String recipeName)
    {
        return new ResponseEntity<Double>(customizedRecipeService.calculateCaloriesOnDailyBasisByRecipe(recipeName),HttpStatus.OK);
    }
}
