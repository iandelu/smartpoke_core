package com.smartpoke.api.feature.user.dto;

import com.smartpoke.api.feature.user.model.Role;
import com.smartpoke.api.feature.user.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean verify;
    private boolean premium;
    private Role role;
    private String picture;

    private UserInfoDto userinfo;
    private LocationDto location;


    public User toEntity() {
        return User.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .verify(verify)
                .premium(premium)
                .role(role)
                .picture(picture)
                .userinfo(userinfo == null ? null : userinfo.toEntity())
                .location(location == null ? null : location.toEntity())
                .build();
    }
    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .verify(user.isVerify())
                .premium(user.isPremium())
                .role(user.getRole())
                .picture(user.getPicture())
                .userinfo(user.getUserinfo() == null ? null : UserInfoDto.fromEntity(user.getUserinfo()))
                .location(user.getLocation() == null ? null : LocationDto.fromEntity(user.getLocation()))
                .build();
    }
}