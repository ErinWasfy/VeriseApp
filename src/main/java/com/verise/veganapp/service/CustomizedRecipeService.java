package com.verise.veganapp.service;

import com.verise.veganapp.entities.Recipe;
import com.verise.veganapp.entities.model.CustomizedListOfDynamicRecipeDTO;
import com.verise.veganapp.entities.model.SuggestedRecipeDTO;
import com.verise.veganapp.repository.DailyRecipeCaloriesRepository;
import com.verise.veganapp.repository.RecipeRepository;
import com.verise.veganapp.repository.WeeklyRecipeCaloriesRepository;
import com.verise.veganapp.utils.MealType;
import com.verise.veganapp.utils.RecipeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class CustomizedRecipeService {
    RecipeRepository recipeRepository;
    WeeklyRecipeCaloriesRepository weeklyRecipeCaloriesRepository;
    DailyRecipeCaloriesRepository dailyRecipeCaloriesRepository;
    @Autowired
    public CustomizedRecipeService(RecipeRepository recipeRepository, WeeklyRecipeCaloriesRepository weeklyRecipeCaloriesRepository,DailyRecipeCaloriesRepository dailyRecipeCaloriesRepository) {
        this.recipeRepository = recipeRepository;
        this.weeklyRecipeCaloriesRepository = weeklyRecipeCaloriesRepository;
        this.dailyRecipeCaloriesRepository=dailyRecipeCaloriesRepository;
    }

    /*
     Display suggested recipes based upon meal type and even updatable on daily basis
     */
    @Transactional(readOnly = true)
    public List<CustomizedListOfDynamicRecipeDTO> fetchDynamicDailyRecipesBasedOnMealType(String mealType)
    {
     return  recipeRepository.fetchAllAvailableRecipesByMealType(mealType);
    }
    public SuggestedRecipeDTO fetchSuggestedRecipe()
    {
       if(LocalDateTime.now().getHour()>= RecipeConstant.BREAKFAST_HOURS_START && LocalDateTime.now().getHour()<=RecipeConstant.BREAKFAST_HOURS_END)
        {
            updateRecipeVisitedDate(MealType.BREAKFAST.name);
            return recipeRepository.fetchSuggestedRecipeBasedOnMorningMeal(LocalDate.now());
        }
        else if(LocalDateTime.now().getHour()>= RecipeConstant.LUNCH_HOURS_START && LocalDateTime.now().getHour()<=RecipeConstant.LUNCH_HOURS_END)
        {
            updateRecipeVisitedDate(MealType.LUNCH.name);
            return recipeRepository.fetchSuggestedRecipeBasedOnLunchMeal();
        }
        else {
           updateRecipeVisitedDate(MealType.DINNER.name);

           return recipeRepository.fetchSuggestedRecipeBasedOnDinnerMeal();
        }
    }
    public void updateRecipeVisitedDate(String mealType)
    {
        List<Recipe> lst=recipeRepository.fetchAllAvailableRecipes();
        for(Recipe rec:lst)
        {
            if(rec.getVisitedDate()!=null && rec.getVisitedDate().equals(LocalDate.now()) && rec.getRecipeMealType().equals(mealType))break;
            if(rec!=null  && rec.getRecipeMealType().equals(mealType))
            {
                Recipe rr=rec;
                rr.setVisitedDate(LocalDate.now());
                recipeRepository.save(rr);
                break;
            }
        }
    }
//    public double calculateCaloriesOnWeeklyBasisByRecipe(String recipeName)
//    {
//        Recipe recipe=recipeRepository.getRecipeData(recipeName);
//        Set<Ingredient> lstOfIngredients=recipeRepository.findById(recipe.getId()).map(rec->rec.getListOfIngredients()).get();
//        return lstOfIngredients.stream().map(ingred->ingred.getCalories()).collect(Collectors.summingDouble(Double::intValue));
//    }
       public double calculateCaloriesOnWeeklyBasisByRecipe(String recipeName)
       {
           Recipe recipe=recipeRepository.fetchRecipeDetailsByName(recipeName);
           return weeklyRecipeCaloriesRepository.findById(recipe.getId()).map(rp->rp.getCalories()).get();
       }
       public double calculateCaloriesOnDailyBasisByRecipe(String recipeName)
       {
          Recipe recipe=recipeRepository.fetchRecipeDetailsByName(recipeName);
           return dailyRecipeCaloriesRepository.findById(recipe.getId()).map(rp->rp.getCalories()).get();

       }
}
