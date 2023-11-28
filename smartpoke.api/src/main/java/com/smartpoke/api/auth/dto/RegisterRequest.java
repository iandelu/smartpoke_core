package com.smartpoke.api.auth.dto;

import com.smartpoke.api.feature.user.dto.LocationDto;
import com.smartpoke.api.feature.user.dto.UserInfoDto;
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
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private LocationDto locationDto;
    private UserInfoDto userInfoDto;

    public User toEntity(){
        return User.builder()
                .username(this.getUsername())
                .password(this.getPassword())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .email(this.getEmail())
                .userinfo(this.userInfoDto.toEntity())
                .location(this.locationDto.toEntity())
                .build();

    }
}
