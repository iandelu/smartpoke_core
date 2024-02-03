package com.smartpoke.api.feature.planner.model;

import com.smartpoke.api.feature.user.model.User;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;


@Entity
@Data
public class MenuWeekUser {

    private Menu menu;
    private User user;
    private Date weekMonday;

}
