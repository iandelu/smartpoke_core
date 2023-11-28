package com.smartpoke.api.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    @Value("${jwt.accessTokenSecret}")
    private String accessTokenSecret;
    @Value("${jwt.expirationDays}")
    private Long expirationDays;

    public String getToken(UserDetails user) {
        return generateToken(new HashMap<>(), user);
    }

    private String generateToken(HashMap<String, Object> extraClaims, UserDetails user) {
        long expirationTimeInMillis = expirationDays * 1000 * 60 * 24; // Convert days to milliseconds
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeInMillis);

        try {
            return Jwts.builder()
                    .claims(extraClaims)
                    .subject(user.getUsername())
                    .expiration(expirationDate)
                    .signWith(Keys.hmacShaKeyFor(accessTokenSecret.getBytes()))
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Error generating the token", e);
        }
    }


}
