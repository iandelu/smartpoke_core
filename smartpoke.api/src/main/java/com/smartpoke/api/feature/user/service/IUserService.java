package com.smartpoke.api.feature.user.service;


import com.smartpoke.api.feature.user.dto.LocationDto;
import com.smartpoke.api.feature.user.dto.UserDto;
import com.smartpoke.api.feature.user.dto.UserInfoDto;
import com.smartpoke.api.feature.user.model.Location;
import com.smartpoke.api.feature.user.model.User;
import com.smartpoke.api.feature.user.model.Userinfo;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface IUserService {

    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto getMyPersonalInfo(HttpServletRequest request);
    UserDto getUserByEmail(String email);
    UserDto updateUser(HttpServletRequest request, UserDto user);
    void deleteUser(Long id);
}
