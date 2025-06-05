package com.KabadiHunt.controllers;


import com.KabadiHunt.dto.UserProfileResponse;

import com.KabadiHunt.models.User;
import com.KabadiHunt.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@RestController
@RequestMapping("/api/user")

public class UserController {
    public static final String UPLOAD_DIR = "uploads"; // stores in project root


    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(Authentication authentication) {
        return ResponseEntity.ok(
                userService.getUserProfile(authentication.getName()));

    }

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> updateUser(
            @RequestParam(required = false) String email,
            @RequestPart(required = false) MultipartFile profileImage,
            Authentication authentication
    ) throws IOException {

        String imagePath = null;

        if (profileImage != null && !profileImage.isEmpty()) {
            String originalFilename = StringUtils.cleanPath(profileImage.getOriginalFilename());
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String newFilename = UUID.randomUUID() + extension;

            File uploadFolder = new File(UPLOAD_DIR);
            if (!uploadFolder.exists()) uploadFolder.mkdirs();

            Path filepath = Paths.get(UPLOAD_DIR, newFilename);
            Files.copy(profileImage.getInputStream(), filepath);

           imagePath = "/uploads/" + newFilename;

        }

        User updatedUser = userService.updateUserProfile(authentication.getName(), email, imagePath);
        return ResponseEntity.ok(updatedUser);
    }



    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(
            @RequestParam String currentPassword,
            @RequestParam String newPassword,
            Authentication authentication
    ) {
        userService.changePassword(
                authentication.getName(),
                currentPassword,
                newPassword
        );
        return ResponseEntity.ok().build();
    }
}