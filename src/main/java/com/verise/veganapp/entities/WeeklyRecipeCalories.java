package com.verise.veganapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "COUNTWeeklyRECIPECALORIES")
public class WeeklyRecipeCalories {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "Total_Amount_Calories")
    private int calories;
    @Column(name = "recipe_name")
    private String recipe_name;
}
