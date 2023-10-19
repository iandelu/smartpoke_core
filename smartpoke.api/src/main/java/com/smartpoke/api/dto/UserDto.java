package com.smartpoke.api.dto;

import com.smartpoke.api.model.users.Userinfo;
import lombok.Data;

@Data
public class UserDto {

    private Integer userId;
    private String userType;
    private String username;
    private boolean verify;
    private boolean premium;
    private String password;
    private LocationDto localization;
    private Userinfo userinfo;
//    private List<Purchase> purchases;
//    private List<Space> spaces;
//    private List<Recipe> recipes;
//    private List<Diet> diets;
}
