package com.smartpoke.api.feature.user.service;

import com.smartpoke.api.common.exceptions.ResourceNotFoundException;
import com.smartpoke.api.common.jwt.JwtService;
import com.smartpoke.api.feature.user.dto.LocationDto;
import com.smartpoke.api.feature.user.dto.UserDto;
import com.smartpoke.api.feature.user.dto.UserInfoDto;
import com.smartpoke.api.feature.user.model.User;
import com.smartpoke.api.feature.user.model.Userinfo;
import com.smartpoke.api.feature.user.repository.LocationRepository;
import com.smartpoke.api.feature.user.repository.UserinfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartpoke.api.feature.user.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtService jwtService;

    @Override
    public UserDto getMyPersonalInfo(HttpServletRequest request) {
        String email = extractEmail(request);
        return getUserByEmail(email);
    }

    @Override
    public UserDto getUserById(Long id) {
        return UserDto.fromEntity(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found")));
    }
    @Override
    public UserDto getUserByEmail(String email) {
        return UserDto.fromEntity(userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found")));
    }
    @Override
    public void deleteUser(HttpServletRequest request) {
        String email = extractEmail(request);
        userRepository.deleteByEmail(email);
    }
    @Override
    public UserDto updateUser(HttpServletRequest request, UserDto user) {
        String email = extractEmail(request);
        Long id = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found")).getId();

        User updatedUser = user.toEntity();
        updatedUser.setId(id);
        return UserDto.fromEntity(userRepository.save(updatedUser));
    }

    private String extractEmail(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        String token = "";
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            token = bearerToken.substring(7);
        }
        return jwtService.extractEmail(token);
    }

}
