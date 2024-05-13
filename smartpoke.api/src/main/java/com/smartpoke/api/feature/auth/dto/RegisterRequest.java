package com.smartpoke.api.feature.auth.dto;

import com.smartpoke.api.feature.user.dto.LocationDto;
import com.smartpoke.api.feature.user.dto.UserInfoDto;
import com.smartpoke.api.feature.user.model.Role;
import com.smartpoke.api.feature.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocationDto locationDto;
    private UserInfoDto userInfoDto;

    public User toEntity(){
        return User.builder()
                .password(this.getPassword())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .email(this.getEmail())
                .userinfo(this.userInfoDto != null ? this.userInfoDto.toEntity() : null)
                .location(this.locationDto != null ? this.locationDto.toEntity() : null)
                .role(Role.USER)
                .verify(false)
                .premium(false)
                .build();

    }
}
