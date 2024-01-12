package com.smartpoke.api.feature.planner.model;

import com.smartpoke.api.feature.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String description;
    private String picture;
    private String lan;
    private Double rating;

    @OneToMany(mappedBy = "menu")
    private List<Meal> meals;
}
