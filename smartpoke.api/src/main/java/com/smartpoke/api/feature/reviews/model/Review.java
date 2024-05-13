package com.smartpoke.api.feature.reviews.model;

import com.smartpoke.api.feature.recipe.model.Recipe;
import com.smartpoke.api.feature.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "review", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "recipe_id"})
})
public class Review {
    @Id
    Long id;
    Double rating;
    String comment;
    String picture;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

}
