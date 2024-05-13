package com.smartpoke.api.feature.user.controller;

import com.smartpoke.api.feature.user.dto.UserDto;
import com.smartpoke.api.feature.user.model.User;
import com.smartpoke.api.feature.user.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMyPersonalInfo(HttpServletRequest request) {
        return ResponseEntity.ok().body(userService.getMyPersonalInfo(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(HttpServletRequest request, @RequestBody UserDto user) {
        return ResponseEntity.ok().body(userService.updateUser(request, user));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(HttpServletRequest request) {
        userService.deleteUser(request);
        return ResponseEntity.ok().body("User deleted successfully");
    }

}
