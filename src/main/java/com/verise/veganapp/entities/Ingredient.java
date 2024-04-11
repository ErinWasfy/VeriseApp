package com.verise.veganapp.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ingredient")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Ingredient {
   @Id
   @GeneratedValue(strategy = GenerationType.TABLE)
   @Column(name = "ingred_id")
   private Long id;

   @Column(name = "ingredient_name")
   private String ingredientName;

   @Column(name = "ingredient_type")
   private String ingredientType;

   @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   private Recipe recipe;
   @Column(name = "quantity")
   private int quantity;
   @Column(name = "calories")
   private double calories;

    public Ingredient()
    {

    }
    public Ingredient(String ingredientName, String ingredientType, Recipe recipe, int quantity, double calories) {
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
        this.recipe = recipe;
        this.quantity = quantity;
        this.calories = calories;
    }
}
