package com.smartpoke.api.service;


import com.smartpoke.api.dto.UserDto;
import com.smartpoke.api.dto.UserInfoDto;
import com.smartpoke.api.model.users.Location;
import com.smartpoke.api.model.users.User;
import com.smartpoke.api.model.users.Userinfo;

import java.util.List;

public interface IUserService {

    User getUserById(Long id);
    List<User> getAllUsers();
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    Userinfo updateUserInfo(Long userId, Userinfo userInfo);
    Location updateLocation(Long userId, Location location);
}
