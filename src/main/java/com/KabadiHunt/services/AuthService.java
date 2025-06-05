//package com.ScrapBridge.services;
//
//import com.ScrapBridge.dto.ApiResponseDto;
//import com.ScrapBridge.dto.GoogleLoginDto;
//import com.ScrapBridge.dto.LoginDto;
//import com.ScrapBridge.dto.RegisterDto;
//import com.ScrapBridge.exception.EwasteAPIException;
//import com.ScrapBridge.models.Owner;
//import com.ScrapBridge.models.User;
//import com.ScrapBridge.repositories.OwnerRepository;
//import com.ScrapBridge.repositories.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//    private final AuthenticationManager authenticationManager;
//    private final UserRepository userRepository;
//    private final EndUserRepository endUserRepository;
//    private final OwnerRepository ownerRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public JwtAuthResponse login(LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginDto.getUsernameOrEmail(),
//                        loginDto.getPassword()
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new JwtAuthResponse(jwtTokenProvider.generateToken(authentication));
//    }
//
//    public ApiResponseDto register(RegisterDto registerDto) {
//        if (userRepository.existsByUsername(registerDto.getUsername())) {
//            throw new EwasteAPIException("Username already exists");
//        }
//        if (userRepository.existsByEmail(registerDto.getEmail())) {
//            throw new EwasteAPIException("Email already exists");
//        }
//
//        User user = new User();
//        user.setUsername(registerDto.getUsername());
//        user.setEmail(registerDto.getEmail());
//        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
//        User savedUser = userRepository.save(user);
//
//        if ("user".equals(registerDto.getRole())) {
//            EndUser endUser = new EndUser();
//            endUser.setUser(savedUser);
//            endUser.setPhone("1234567890"); // Default phone
//            endUserRepository.save(endUser);
//        } else if ("recycler".equals(registerDto.getRole())) {
//            Owner owner = new Owner();
//            owner.setUser(savedUser);
//            owner.setOrganisationName(registerDto.getUsername());
//            owner.setPhone("1234567890"); // Default phone
//            ownerRepository.save(owner);
//        }
//
//        return new ApiResponseDto(true, "User registered successfully");
//    }
//
//    public JwtAuthResponse googleLogin(GoogleLoginDto googleLoginDto) {
//        // Implement Google OAuth2 login logic
//        // This would typically involve:
//        // 1. Verifying the Google token
//        // 2. Getting user info from Google
//        // 3. Creating or updating local user
//        // 4. Generating JWT token
//        return null; // Replace with actual implementation
//    }
//}
