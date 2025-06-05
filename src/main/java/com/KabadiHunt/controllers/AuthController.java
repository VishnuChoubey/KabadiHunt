package com.KabadiHunt.controllers;

//import com.ScrapBridge.dto.*;
//import com.ScrapBridge.services.AuthService;
//import jakarta.validation.Valid;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final AuthService authService;
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtTokenResponse> login(@RequestBody LoginDto loginDto) {
//        return ResponseEntity.ok(authService.login(loginDto));
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<ApiResponseDto> register(@Valid @RequestBody RegisterDto registerDto) {
//        return ResponseEntity.ok(authService.register(registerDto));
//    }
//
//    @PostMapping("/google-login")
//    public ResponseEntity<JwtTokenResponse> googleLogin(@RequestBody GoogleLoginDto googleLoginDto) {
//        return ResponseEntity.ok(authService.googleLogin(googleLoginDto));
//    }
//
//    @GetMapping("/check-authentication")
//    public ResponseEntity<ApiResponseDto> checkAuthentication() {
//        return ResponseEntity.ok(new ApiResponseDto(true, "User is authenticated"));
//    }
//}
