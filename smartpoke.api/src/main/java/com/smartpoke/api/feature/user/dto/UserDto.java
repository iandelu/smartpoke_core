package com.smartpoke.api.feature.user.dto;

import com.smartpoke.api.feature.user.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean verify;
    private boolean premium;
    private Role role;

    private UserInfoDto userinfo;
    private LocationDto location;
}