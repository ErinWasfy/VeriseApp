package com.verise.veganapp.entities;

import com.fasterxml.jackson.annotation.*;
import com.verise.veganapp.utils.MealType;
import com.verise.veganapp.utils.RecipeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "recipe")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Recipe {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;

   @Column(name = "recipe_name")
   private String recipeName;

   @Column(name = "recipe_description")
   private String recipeDescription;

   @Column(name = "recipe_meal_type")
   private String recipeMealType;

   @JsonIgnore
   @Column(name = "created_date")
   private Date createdDate;

   @Column(name = "recipe_rate")
   private int recipeRate;

   @Column(name = "recipe_status")
   private String recipeStatus;

   @Column(name = "visited_date")
   private LocalDate visitedDate;

    @OneToMany(mappedBy = "recipe")
    private Set<Ingredient> listOfIngredients;

    @Column(name = "visited_status")
    private boolean visited;

    public Recipe() {
    }

//    public Recipe(String recipeName, String recipeDescription, String recipeMealType, Date createdDate, int recipeRate, String recipeStatus, Date visitedDate, Set<Ingredient> listOfIngredients) {
//        this.recipeName = recipeName;
//        this.recipeDescription = recipeDescription;
//        this.recipeMealType = recipeMealType;
//        this.createdDate = createdDate;
//        this.recipeRate = recipeRate;
//        this.recipeStatus = recipeStatus;
//        this.visitedDate = visitedDate;
//        this.listOfIngredients = listOfIngredients;
//    }

    public void addIngredient(Ingredient ingredient)
    {
        if(listOfIngredients==null)
            listOfIngredients=new HashSet<>();
        listOfIngredients.add(ingredient);
        ingredient.setRecipe(this);
    }

}
