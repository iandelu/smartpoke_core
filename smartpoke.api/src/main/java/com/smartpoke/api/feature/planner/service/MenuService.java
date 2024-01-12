package com.smartpoke.api.feature.planner.service;

import com.smartpoke.api.feature.planner.model.Meal;
import com.smartpoke.api.feature.planner.model.MealType;
import com.smartpoke.api.feature.planner.model.Menu;
import com.smartpoke.api.feature.user.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MenuService implements IMenuService{
    @Override
    public Menu createWeeklyMenu(User user, LocalDate startDate) {
        Menu menu = new Menu();
        menu.setUser(user);
        menu.setStartDate(startDate);
        menu.setEndDate(startDate.plusDays(6)); // Asumiendo una semana de duración

        List<Meal> meals = new ArrayList<>();
        LocalDate date = startDate;
        for (int i = 0; i < 7; i++) { // 7 días de la semana
            meals.addAll(createMealsForDay(menu, date));
            date = date.plusDays(1);
        }

        menu.setMeals(meals);

        // Aquí guardarías el menú en la base de datos usando un repositorio
        // menuRepository.save(menu);

        return menu;
    }

    @Override
    public List<Meal> createMealsForDay(Menu menu, LocalDate date) {
        List<Meal> mealsForDay = new ArrayList<>();
        for (MealType mealType : MealType.values()) {
            Meal meal = new Meal();
            meal.setMealType(mealType);
            meal.setDate(date);
            meal.setMenu(menu);
            // Configura las recetas aquí si es necesario
            mealsForDay.add(meal);
        }
        return mealsForDay;
    }
}
