package com.verise.veganapp.utils;

public enum MealType {
    BREAKFAST("Breakfast"),LUNCH("Lunch"),DINNER("Dinner");
    public String name;
     MealType(String nme)
    {
        this.name=nme;
    }

    public String getName() {
        return name;
    }
}
