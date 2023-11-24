package com.smartpoke.api.feature.user.service;


import com.smartpoke.api.feature.user.model.Location;
import com.smartpoke.api.feature.user.model.User;
import com.smartpoke.api.feature.user.model.Userinfo;

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
