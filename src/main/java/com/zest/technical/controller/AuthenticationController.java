package com.zest.technical.controller;

import com.zest.technical.service.AuthenticationService;
import com.zest.technical.data.AuthenticationRequest;
import com.zest.technical.data.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "APIs for user login and token refresh")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Operation(summary = "Login and get JWT token", description = "Authenticates user and returns JWT access and refresh tokens.")
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.login(authenticationRequest);
    }

    @Operation(summary = "Refresh JWT token", description = "Uses the refresh token to generate a new access token.")
    @PostMapping("/refresh-token")
    public AuthenticationResponse refreshToken(@RequestBody String refreshToken) {
        return authenticationService.refreshToken(refreshToken);
    }
}
