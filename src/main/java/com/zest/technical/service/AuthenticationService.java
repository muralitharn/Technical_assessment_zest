package com.zest.technical.service;


import com.zest.technical.config.JWTConfig;
import com.zest.technical.data.AuthenticationRequest;
import com.zest.technical.data.AuthenticationResponse;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import java.util.Date;

import io.jsonwebtoken.*;

@Service
public class AuthenticationService {

    private static final SecretKey SECRET_KEY = JWTConfig.SECRET_KEY;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        if ("admin".equals(authenticationRequest.getUsername()) && "password".equals(authenticationRequest.getPassword())) {
            String accessToken = generateToken(authenticationRequest.getUsername(), "ROLE_ADMIN");
            String refreshToken = generateRefreshToken(authenticationRequest.getUsername(), "ROLE_ADMIN");
            return new AuthenticationResponse(accessToken, refreshToken);

        } else if ("user".equals(authenticationRequest.getUsername()) && "password".equals(authenticationRequest.getPassword())) {
            String accessToken = generateToken(authenticationRequest.getUsername(), "ROLE_USER");
            String refreshToken = generateRefreshToken(authenticationRequest.getUsername(), "ROLE_USER");
            return new AuthenticationResponse(accessToken, refreshToken);
        }
        throw new RuntimeException("Invalid credentials");
    }

    private String generateToken(String username, String role) {
        return Jwts.builder().setSubject(username).claim("roles", role).setIssuedAt(new Date())  // Generate access token (15 minutes expiration)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15)).signWith(SECRET_KEY).compact();
    }

    private String generateRefreshToken(String username, String role) {
        return Jwts.builder().setSubject(username).claim("roles", role).setIssuedAt(new Date()) // Generate refresh token (1 day expiration)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 1)).signWith(SECRET_KEY).compact();
    }

    public AuthenticationResponse refreshToken(String refreshToken) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(refreshToken).getBody();

        String username = claims.getSubject();
        String role = claims.get("roles", String.class);

        if (role == null) {
            throw new RuntimeException("No role found in the refresh token");
        }

        String newAccessToken = generateToken(username, role);
        String newRefreshToken = generateRefreshToken(username, role);

        return new AuthenticationResponse(newAccessToken, newRefreshToken);
    }

}

