package com.smartpoke.api.model.users;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "userProfile")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userType;
    private String username;
    private boolean verify;
    private boolean premium;
    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Location location;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Userinfo userinfo;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "id_user")
//    private List<Purchase> purchases;
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "id_usuario")
//    private List<Space> spaces;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Recipe> recipes;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Diet> diets;

}
