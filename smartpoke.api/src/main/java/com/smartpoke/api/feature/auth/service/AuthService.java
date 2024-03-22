package com.smartpoke.api.feature.auth.service;

import com.smartpoke.api.common.exceptions.EmailInUseException;
import com.smartpoke.api.common.exceptions.InvalidPasswordException;
import com.smartpoke.api.common.utils.PasswordValidator;
import com.smartpoke.api.feature.auth.dto.AuthResponse;
import com.smartpoke.api.feature.auth.dto.LoginRequest;
import com.smartpoke.api.feature.auth.dto.RegisterRequest;
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
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(ResourceNotFoundException::new);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    public AuthResponse register(RegisterRequest request){

        //Check if password is valid
        if (!PasswordValidator.isValid(request.getPassword())){
            throw new InvalidPasswordException();
        }

        //Check if email is already in use
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new EmailInUseException();
        }

        //Encode the password and save the user
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userRepository.save(request.toEntity());

        //Return the token
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
