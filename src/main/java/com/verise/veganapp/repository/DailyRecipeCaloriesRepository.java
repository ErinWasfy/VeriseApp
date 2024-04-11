package com.verise.veganapp.repository;

import com.verise.veganapp.entities.DailyRecipeCalories;
import com.verise.veganapp.entities.WeeklyRecipeCalories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyRecipeCaloriesRepository extends JpaRepository<DailyRecipeCalories,Long> {
}
