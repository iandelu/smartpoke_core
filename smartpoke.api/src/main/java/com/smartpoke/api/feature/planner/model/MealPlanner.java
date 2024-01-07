package com.smartpoke.api.feature.planner.model;

import com.smartpoke.api.feature.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class MealPlanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numDays;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String description;
    private String picture;
    private String lan;
    private Double rating;

    @OneToMany(mappedBy = "mealPlanner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Meal> meals;
}
