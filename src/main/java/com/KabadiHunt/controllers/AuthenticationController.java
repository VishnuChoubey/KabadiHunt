package com.KabadiHunt.controllers;




import com.KabadiHunt.dto.AuthenticationRequest;
import com.KabadiHunt.dto.AuthenticationResponse;
import com.KabadiHunt.dto.RefreshTokenRequest;
import com.KabadiHunt.dto.RegisterRequest;

import com.KabadiHunt.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // You missed this
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(
            @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authenticationService.refreshToken(request.getRefreshToken()));
    }





//    @PostMapping("/google")
//    public ResponseEntity<AuthenticationResponse> googleAuth(
//            @RequestBody GoogleAuthRequest request) {
//        return ResponseEntity.ok(authenticationService.authenticateWithGoogle(request));
//    }
}
