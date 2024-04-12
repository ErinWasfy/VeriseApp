package com.verise.veganapp.repository;

import com.verise.veganapp.entities.Recipe;
import com.verise.veganapp.entities.model.CustomizedListOfDynamicRecipeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {

    @Transactional(readOnly = true)
    @Query(value="select top 20 recp.id,recp.recipe_name,recp.recipe_description,recp.recipe_meal_type,recp.created_date,recp.recipe_rate,recp.recipe_status,recp.visited_date,recp.visited_status from recipe recp where recp.recipe_status='Ready'",nativeQuery = true)
    List<Recipe> fetchAllAvailableRecipes();

    @Query(value="select recp.id,recp.recipe_name,recp.recipe_description,recp.recipe_meal_type,recp.created_date,recp.recipe_rate,recp.recipe_status,recp.visited_date from recipe recp where recp.recipe_status='Ready' and recp.recipe_name=?1",nativeQuery = true)
    Recipe fetchRecipeDetailsByName(String recipeName);
    /*
    fetch recipes based upon meal type
     */
     @Query(value="select new com.verise.veganapp.entities.model.CustomizedListOfDynamicRecipeDTO(recp.id,recp.recipeName,recp.recipeDescription,recp.recipeRate,recp.recipeStatus,recp.recipeMealType,recp.visitedDate) from Recipe recp where recp.recipeStatus='Ready' and recp.recipeRate>2 and recp.recipeMealType=?1")
      List<com.verise.veganapp.entities.model.CustomizedListOfDynamicRecipeDTO> fetchAllAvailableRecipesByMealType(String mealType);

    @Query(value="select new com.verise.veganapp.entities.model.CustomizedListOfDynamicRecipeDTO(recp.id,recp.recipeName,recp.recipeDescription,recp.recipeRate,recp.recipeStatus,recp.recipeMealType,recp.visitedDate) from Recipe recp where recp.recipeStatus='Ready' and recp.recipeRate>2 and recp.recipeName=?1")
    com.verise.veganapp.entities.model.CustomizedListOfDynamicRecipeDTO fetchRecipeByName(String recipeName);

     /*
       fetch recipes based upon morning time
      */
//     @Query(value="update recipe r set r.visited_date=GETDATE() where r.recipe_meal_type=?1")
//     void updateVisitedRecipe(String mealType);

     @Query(value="select new com.verise.veganapp.entities.model.SuggestedRecipeDTO(recp.recipeName) from Recipe recp where recp.recipeStatus='Ready' and recp.recipeMealType='Breakfast' and recp.recipeRate>2 and recp.visitedDate=:date")
      com.verise.veganapp.entities.model.SuggestedRecipeDTO fetchSuggestedRecipeBasedOnMorningMeal(@Param("date") LocalDate date);

     @Query(value="select new com.verise.veganapp.entities.model.SuggestedRecipeDTO(recp.recipeName) from Recipe recp where recp.recipeStatus='Ready' and recp.recipeMealType='Dinner' and recp.recipeRate>2 and recp.visitedDate is not null")
      com.verise.veganapp.entities.model.SuggestedRecipeDTO fetchSuggestedRecipeBasedOnDinnerMeal();

     @Query(value="select new com.verise.veganapp.entities.model.SuggestedRecipeDTO(recp.recipeName) from Recipe recp where recp.recipeStatus='Ready' and recp.recipeMealType='Lunch' and recp.recipeRate>2 and recp.visitedDate is not null")
      com.verise.veganapp.entities.model.SuggestedRecipeDTO fetchSuggestedRecipeBasedOnLunchMeal();

//     @Query(value="select recp.id,recp.recipe_name,recp.recipe_description,recp.recipe_rate,recp.recipe_status,recp.created_date,recp.recipe_meal_type from recipe recp where recp.recipe_status='Ready' and recp.recipe_rate>2 and recp.recipe_name=?1",nativeQuery = true)
//      public Recipe getRecipeData(String recipeName);
}
