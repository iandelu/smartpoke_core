package com.smartpoke.api.feature.user.dto;

import com.smartpoke.api.feature.user.model.Userinfo;
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

}
