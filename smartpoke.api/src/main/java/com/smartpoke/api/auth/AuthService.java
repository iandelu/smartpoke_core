package com.smartpoke.api.auth;

import com.smartpoke.api.auth.dto.AuthResponse;
import com.smartpoke.api.auth.dto.LoginRequest;
import com.smartpoke.api.auth.dto.RegisterRequest;
import com.smartpoke.api.common.exceptions.ResourceNotFoundException;
import com.smartpoke.api.common.jwt.JwtService;
import com.smartpoke.api.feature.user.model.User;
import com.smartpoke.api.feature.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails userDetails = userRepository.findByUsername(request.getUsername()).orElseThrow(ResourceNotFoundException::new);
        return AuthResponse.builder()
                .token(jwtService.getToken(userDetails))
                .build();
    }

    public AuthResponse register(RegisterRequest request){
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userRepository.save(request.toEntity());

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
