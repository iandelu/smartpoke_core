package com.smartpoke.api.feature.planner.service;

import com.smartpoke.api.feature.planner.model.Meal;
import com.smartpoke.api.feature.planner.model.Menu;
import com.smartpoke.api.feature.user.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IMenuService {
    Menu createWeeklyMenu(User user, LocalDate startDate);
    List<Meal> createMealsForDay(Menu menu, LocalDate date);

}
