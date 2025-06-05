package com.KabadiHunt.services;

import java.util.Optional;

import com.KabadiHunt.dto.UserProfileResponse;
import com.KabadiHunt.exception.ResourceNotFoundException;
import com.KabadiHunt.models.User;
import com.KabadiHunt.repositories.UserRepository;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       FileStorageService fileStorageService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserProfileResponse getUserProfile(String username) {
        // Security check - user can only access their own profile
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!currentUsername.equals(username)) {
            throw new AccessDeniedException("You can only view your own profile");
        }

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return mapToProfileResponse(user);
    }

    public void changePassword(String username, String currentPassword, String newPassword) {
        // Security check
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!currentUsername.equals(username)) {
            throw new AccessDeniedException("You can only change your own password");
        }

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new SecurityException("Current password is incorrect");
        }

        if (currentPassword.equals(newPassword)) {
            throw new IllegalArgumentException("New password must be different from current password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    private UserProfileResponse mapToProfileResponse(User user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .profileImageUrl(user.getProfileImageUrl())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public User updateUserProfile(String userId, @Email String email, String imageFile) {
        User user = userRepository.findByEmail(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Security check
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!currentUsername.equals(user.getEmail())) {
            throw new AccessDeniedException("You can only update your own profile");
        }

        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }

//        if (imageFile != null && !imageFile.isEmpty()) {
//            validateImageFile(imageFile);
//            String imagePath = fileStorageService.storeFile(imageFile);
//            user.setImage(imagePath);
//        }
          if(imageFile!=null){
              user.setProfileImageUrl(imageFile);
          }
        return userRepository.save(user);
    }

//    private void validateImageFile(MultipartFile file) {
//        if (file.getSize() > 5 * 1024 * 1024) { // 5MB limit
//            throw new IllegalArgumentException("File size must be less than 5MB");
//        }
//
//        String contentType = file.getContentType();
//        if (contentType == null ||
//                !(contentType.equals("image/jpeg") ||
//                        contentType.equals("image/png") ||
//                        contentType.equals("image/gif"))) {
//            throw new IllegalArgumentException("Only JPG, PNG or GIF images are allowed");
//        }
//    }

    // Admin-only methods
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }
//
//    public Optional<User> getUserByUsername(String username) {
//        return (User)userRepository.findByUsername(username);
//    }
}